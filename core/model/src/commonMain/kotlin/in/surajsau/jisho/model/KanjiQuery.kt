package `in`.surajsau.jisho.model

public sealed interface KanjiQuery {

    public data class Freq(val from: Long, val to: Long) : KanjiQuery

    public data class Grade(val grade: Int) : KanjiQuery
    public object AllSchool : KanjiQuery
    public object All : KanjiQuery
}
