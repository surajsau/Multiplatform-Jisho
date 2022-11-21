package `in`.surajsau.jisho.model.mapper.kanjidic

import `in`.surajsau.jisho.model.kanjidic.Meaning

public fun String.meaningFromDb(): List<Meaning> {
    return split(";")
        .map {
            val (value, type) = it.split("-")
            Meaning(value, type)
        }
}