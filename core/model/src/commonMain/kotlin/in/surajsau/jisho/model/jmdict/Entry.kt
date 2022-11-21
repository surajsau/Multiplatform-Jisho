package `in`.surajsau.jisho.model.jmdict

public data class Entry(
    val id: Long,
    val kanjis: List<Kanji>,
    val readings: List<JReading>,
    val senses: List<Sense>,
)
