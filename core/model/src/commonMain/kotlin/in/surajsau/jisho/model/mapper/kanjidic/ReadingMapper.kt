package `in`.surajsau.jisho.model.mapper.kanjidic

import `in`.surajsau.jisho.model.kanjidic.KReading

public fun String.readingFromDb(): List<KReading> {
    return split(";")
        .map {
            val (value, type) = it.split("-")
            KReading(value, type)
        }
}