package `in`.surajsau.jisho.download

interface DownloadManager {
    fun checkIfDatabaseExists(): Boolean
    fun downloadFile(onCompletion: (FileStatus) -> Unit)
    fun extractFile(onCompletion: (FileStatus) -> Unit)
}