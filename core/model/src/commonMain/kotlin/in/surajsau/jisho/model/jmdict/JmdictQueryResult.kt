package `in`.surajsau.jisho.model.jmdict

public data class JmdictQueryResult(
    val id: Long,
    val kanjiString: String,
    val readingString: String,
    val readingRestrictionString: String,
    val glossString: String,
)
