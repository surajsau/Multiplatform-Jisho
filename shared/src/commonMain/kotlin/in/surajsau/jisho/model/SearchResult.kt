package `in`.surajsau.jisho.model

data class SearchResult(
    val id: Long,
    val value: String,
    val reading: String,
    val meanings: String
)