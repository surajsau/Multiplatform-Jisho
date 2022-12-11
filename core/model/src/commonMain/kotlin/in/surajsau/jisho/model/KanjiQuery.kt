package `in`.surajsau.jisho.model

public sealed interface KanjiQuery

public data class KanjiQueryFreq(val from: Long, val to: Long) : KanjiQuery

public data class KanjiQueryGrade(val grade: Int) : KanjiQuery
public object KanjiQueryAllSchool : KanjiQuery
public object KanjiQueryAll : KanjiQuery
