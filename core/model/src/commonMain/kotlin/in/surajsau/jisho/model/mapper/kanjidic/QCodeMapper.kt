package `in`.surajsau.jisho.model.mapper.kanjidic

import `in`.surajsau.jisho.model.kanjidic.QCode

public fun String.qCodefromDb(): List<QCode> {
    return split(";")
        .map {
            val (value, type) = it.split("-")
            QCode(value, type)
        }
}