package `in`.surajsau.jisho.model

data class BucketResult(
    val id: Long,
    val name: String,
    val count: Int
) {
    val displayTitle: String
        get() = "$name ($count)"
}