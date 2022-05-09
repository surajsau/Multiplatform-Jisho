package `in`.surajsau.jisho.model

sealed interface KanjiQuery {
    data class Freq(val from: Long, val to: Long) : KanjiQuery
    data class Grade(val grade: Int) : KanjiQuery
    object AllSchool: KanjiQuery
    object All : KanjiQuery
}