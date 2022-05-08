package `in`.surajsau.jisho.data.mapper.kanjidic

import `in`.surajsau.jisho.data.model.kanjidic.KReading

fun String.readingFromDb(): List<KReading> {
    return split(";")
        .map {
            val (value, type) = it.split("-")
            KReading(value, type)
        }
}