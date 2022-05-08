package `in`.surajsau.jisho.model

sealed interface KanjiQuery {
    data class Freq(val from: Long, val to: Long) : KanjiQuery
    data class Grade(val value: String) : KanjiQuery
    object All : KanjiQuery
}