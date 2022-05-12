package `in`.surajsau.jisho.android.download

import android.content.Context
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.InternalCoroutinesApi
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.zip.GZIPInputStream
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class Downloader(private val context: Context) {

    private val storage by lazy { Firebase.storage }

    private val databaseDir: File
        get() = File(context.dataDir, "databases")

    fun checkIfDatabaseExists(): Boolean {
        return File(databaseDir, "jmdict.db").exists()
    }

    @OptIn(InternalCoroutinesApi::class)
    suspend fun downloadFile(name: String) = suspendCoroutine<FileStatus> { cont ->
        val (prefix, suffix) = name.split(".")

        val reference = storage.reference.child(name)
        val localFile = File(context.filesDir, "$prefix.$suffix")

        reference.getFile(localFile)
            .addOnSuccessListener {
                cont.resume(FileStatus.Downloaded)
            }.addOnFailureListener {
                cont.resume(FileStatus.Error(it))
            }
    }

    @OptIn(InternalCoroutinesApi::class)
    suspend fun extractFile(name: String) = suspendCoroutine<FileStatus> { cont ->
        val result = runCatching {
            val localFile = File(context.filesDir, name)
            val destFile = File(databaseDir, "jmdict.db")

            val zis = GZIPInputStream(FileInputStream(localFile))

            val bos = BufferedOutputStream(FileOutputStream(destFile))
            val bytesIn = ByteArray(BufferSize)

            var read: Int
            while (zis.read(bytesIn).also { read = it } != -1) {
                bos.write(bytesIn, 0, read)
            }
            bos.close()
        }.onFailure { it.printStackTrace() }

        if (result.isSuccess) {
            cont.resume(FileStatus.Extracted)
        } else {
            cont.resume(FileStatus.Error(Exception(result.exceptionOrNull()!!)))
        }
    }

    companion object {
        private const val BufferSize = 4096
    }
}
