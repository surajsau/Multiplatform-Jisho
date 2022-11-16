package `in`.surajsau.jisho.download.downloader

sealed interface FileStatus {
    object Downloaded : FileStatus
    object Extracted : FileStatus
    data class Error(val exception: Exception) : FileStatus
}