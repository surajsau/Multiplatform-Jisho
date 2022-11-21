package `in`.surajsau.jisho.model.mapper.kanjidic

import `in`.surajsau.jisho.model.kanjidic.Radical

public fun String.radicalFromDb(): List<Radical> {
    return split(";")
        .map {
            val (value, type) = split("-")
            Radical(value, type)
        }
}