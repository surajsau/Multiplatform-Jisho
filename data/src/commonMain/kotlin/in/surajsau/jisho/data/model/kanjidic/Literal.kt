package `in`.surajsau.jisho.data.model.kanjidic

data class Literal(
    val value: String,
    val radical: List<Radical>,
    val grade: Grade?,
    val jlpt: Jlpt?,
    val freq: Freq?,
    val strokeCount: StrokeCount,
    val radicalNames: List<RadicalName>,
    val readings: List<KReading>,
    val meanings: List<Meaning>,
    val nanoris: List<Nanori>,
    val dicReferences: List<DicReference>,
    val variants: List<Variant>,
    val qCodes: List<QCode>,
)