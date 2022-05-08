package `in`.surajsau.jisho.data.mapper.kanjidic

import `in`.surajsau.jisho.data.model.kanjidic.QCode

fun String.qCodefromDb(): List<QCode> {
    return split(";")
        .map {
            val (value, type) = it.split("-")
            QCode(value, type)
        }
}