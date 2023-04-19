package `in`.surajsau.jisho.app

import android.content.Context
import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import `in`.surajsau.jisho.download.DownloadManager
import `in`.surajsau.jisho.download.FileStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.zip.GZIPInputStream

class AndroidDownloadManager constructor(
    private val context: Context,
    private val ioScope: CoroutineScope
): DownloadManager {

    private val storage by lazy { Firebase.storage }

    private val databaseDir: File
        get() = File(context.dataDir, "databases")

    override fun checkIfDatabaseExists(): Boolean {
        return File(databaseDir, DbFileName).exists()
    }

    override fun downloadFile(onCompletion: (FileStatus) -> Unit) {
        val reference = storage.reference.child(ZipFileName)
        val localFile = File(context.filesDir, ZipFileName)

        reference.getFile(localFile)
            .addOnSuccessListener {
                onCompletion(FileStatus.Downloaded)
            }.addOnFailureListener {
                Log.e("Downloader", "downloadFile error $it")
                onCompletion(FileStatus.Error(it))
            }
    }

    override fun extractFile(onCompletion: (FileStatus) -> Unit) {
        ioScope.launch {
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
                onCompletion(FileStatus.Extracted)
            } else {
                onCompletion(FileStatus.Error(Exception(result.exceptionOrNull()!!)))
            }
        }
    }

    companion object {
        private const val BufferSize = 4096
        private const val ZipFileName = "jmdict.db.gz"
        private const val DbFileName = "jmdict.db"
    }

}