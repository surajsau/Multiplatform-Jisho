package `in`.surajsau.jisho.model

sealed interface BucketQuery {
    val id: Long
    val bucketId: Long

    data class Entry(override val id: Long, override val bucketId: Long): BucketQuery
    data class Kanji(override val id: Long, override val bucketId: Long): BucketQuery
}