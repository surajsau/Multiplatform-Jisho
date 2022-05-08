package `in`.surajsau.jisho.data.mapper.kanjidic

import `in`.surajsau.jisho.data.model.kanjidic.Radical

fun String.radicalFromDb(): List<Radical> {
    return split(";")
        .map {
            val (value, type) = split("-")
            Radical(value, type)
        }
}