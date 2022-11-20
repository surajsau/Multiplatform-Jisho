package `in`.surajsau.jisho.download

sealed interface FileStatus {
    object Downloaded : FileStatus
    object Extracted : FileStatus
    data class Error(val exception: Exception) : FileStatus
}