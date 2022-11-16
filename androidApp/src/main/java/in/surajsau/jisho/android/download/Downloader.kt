package `in`.surajsau.jisho.android.download

import android.content.Context
import android.util.Log
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

    suspend fun downloadFile(name: String) = suspendCoroutine { cont ->
        val reference = storage.reference.child(name)
        val localFile = File(context.filesDir, name)

        reference.getFile(localFile)
            .addOnSuccessListener {
                cont.resume(FileStatus.Downloaded)
            }.addOnFailureListener {
                Log.e("Downloader", "downloadFile error $it")
                cont.resume(FileStatus.Error(it))
            }
    }

    suspend fun extractFile(name: String) = suspendCoroutine { cont ->
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
        }.onFailure {
            Log.e("Downloader", "extractFile error $it")
            it.printStackTrace()
        }

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
