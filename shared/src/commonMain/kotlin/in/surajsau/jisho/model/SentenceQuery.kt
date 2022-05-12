package `in`.surajsau.jisho.model

data class SentenceQuery(
    private val word: String,
) {

    val query: String
        get() = "%$word%"
}
