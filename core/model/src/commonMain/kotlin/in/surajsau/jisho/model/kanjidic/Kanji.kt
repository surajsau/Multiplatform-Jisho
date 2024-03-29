package `in`.surajsau.jisho.model.kanjidic

public data class Kanji(
    val id: Long,
    val value: String,
    val radical: List<Radical>,
    val grade: Grade?,
    val jlpt: Jlpt?,
    val freq: Freq?,
    val strokeCount: StrokeCount,
    val radicalNames: List<RadicalName>,
    val readings: List<KanjiReading>,
    val meanings: List<Meaning>,
    val nanoris: List<Nanori>,
    val dicReferences: List<DicReference>,
    val variants: List<Variant>,
    val qCodes: List<QCode>,
)