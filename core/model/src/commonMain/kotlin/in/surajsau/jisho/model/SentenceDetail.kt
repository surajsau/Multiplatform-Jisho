package `in`.surajsau.jisho.model

public data class SentenceDetail(
    val id: Long,
    val japanese: String,
    val english: String,
    val note: NoteResult?,
)
