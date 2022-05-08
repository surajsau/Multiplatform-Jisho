package `in`.surajsau.jisho.data.model.jmdict

data class Kanji(
    val value: String,
    val info: List<`in`.surajsau.jisho.data.model.jmdict.Info>,
    val priority: List<Priority>,
)