package `in`.surajsau.jisho.data.model.jmdict

data class Entry(
    val id: Long,
    val kanjis: List<Kanji>,
    val readings: List<JReading>,
    val senses: List<Sense>,
    val noteId: Long?,
    val bucketId: Long?,
)
