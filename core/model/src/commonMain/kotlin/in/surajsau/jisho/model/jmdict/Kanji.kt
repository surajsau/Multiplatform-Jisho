package `in`.surajsau.jisho.model.jmdict

public data class Kanji(
    val value: String,
    val info: List<Info>,
    val priority: List<Priority>,
)