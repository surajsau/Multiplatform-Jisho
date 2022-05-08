package `in`.surajsau.jisho.data.mapper.kanjidic

import `in`.surajsau.jisho.data.model.kanjidic.Meaning

fun String.meaningFromDb(): List<Meaning> {
    return split(";")
        .map {
            val (value, type) = it.split("-")
            Meaning(value, type)
        }
}