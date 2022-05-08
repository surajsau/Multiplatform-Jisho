package `in`.surajsau.jisho.android.download

sealed interface FileStatus {
    object Downloaded : FileStatus
    object Extracted : FileStatus
    data class Error(val exception: Exception) : FileStatus
}