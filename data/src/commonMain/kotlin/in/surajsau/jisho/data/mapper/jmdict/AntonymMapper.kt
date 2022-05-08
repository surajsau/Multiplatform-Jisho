package `in`.surajsau.jisho.data.mapper.jmdict

import `in`.surajsau.jisho.data.model.jmdict.Antonym

fun String.antonymFromDb(): List<Antonym> = split("|").map {
    Antonym(it)
}