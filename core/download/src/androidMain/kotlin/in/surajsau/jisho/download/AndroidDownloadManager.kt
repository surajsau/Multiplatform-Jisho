package `in`.surajsau.jisho.download

import android.content.Context
import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import org.koin.android.ext.koin.androidApplication
import org.koin.core.scope.Scope
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.zip.GZIPInputStream
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AndroidDownloadManager constructor(
    private val context: Context
): DownloadManager {

    private val storage by lazy { Firebase.storage }

    private val databaseDir: File
        get() = File(context.dataDir, "databases")

    override fun checkIfDatabaseExists(): Boolean {
        return File(databaseDir, DbFileName).exists()
    }

    override suspend fun downloadFile() = suspendCoroutine { cont ->
        val reference = storage.reference.child(ZipFileName)
        val localFile = File(context.filesDir, ZipFileName)

        reference.getFile(localFile)
            .addOnSuccessListener {
                cont.resume(FileStatus.Downloaded)
            }.addOnFailureListener {
                Log.e("Downloader", "downloadFile error $it")
                cont.resume(FileStatus.Error(it))
            }
    }

    override suspend fun extractFile() = suspendCoroutine { cont ->
        val result = runCatching {
            val localFile = File(context.filesDir, ZipFileName)
            val destFile = File(databaseDir, DbFileName)

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
        private const val ZipFileName = "jmdict.db.gz"
        private const val DbFileName = "jmdict.db"
    }

}

actual fun Scope.downloadManager(): DownloadManager = AndroidDownloadManager(context = androidApplication())