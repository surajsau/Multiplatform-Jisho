package `in`.surajsau.jisho.data.model.jmdict

data class JmdictQueryResult(
    val id: Long,
    val kanjiString: String,
    val readingString: String,
    val readingRestrictionString: String,
    val glossString: String,
)
