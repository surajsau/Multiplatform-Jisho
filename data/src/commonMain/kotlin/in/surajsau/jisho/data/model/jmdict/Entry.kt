package `in`.surajsau.jisho.data.model.jmdict

data class Entry(
    val id: Long,
    val kanjis: List<Kanji>,
    val readings: List<JReading>,
    val senses: List<Sense>,
)
