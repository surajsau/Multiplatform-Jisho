package `in`.surajsau.jisho.model

public data class SentenceQuery(
    private val word: String,
) {

    val query: String
        get() = "%$word%"
}
