package `in`.surajsau.jisho.data.model.jmdict

data class Sense(
    val glosses: List<Gloss>,
    val particleOfSpeeches: List<Info>,
    val antonyms: List<Antonym>,
    val dialects: List<Dialect>,
    val fields: List<Info>,
    val example: List<Example>,
)
