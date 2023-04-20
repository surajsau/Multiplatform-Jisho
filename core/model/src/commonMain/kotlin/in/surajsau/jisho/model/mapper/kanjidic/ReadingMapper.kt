package `in`.surajsau.jisho.model.mapper.kanjidic

import `in`.surajsau.jisho.model.kanjidic.KanjiReading

public fun String.readingFromDb(): List<KanjiReading> {
    return split(";")
        .map {
            val (value, type) = it.split("-")
            KanjiReading(value, type)
        }
}