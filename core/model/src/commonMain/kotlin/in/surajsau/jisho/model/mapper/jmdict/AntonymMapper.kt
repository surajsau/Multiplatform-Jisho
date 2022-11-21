package `in`.surajsau.jisho.model.mapper.jmdict

import `in`.surajsau.jisho.model.jmdict.Antonym

public fun String.antonymFromDb(): List<Antonym> = split("|").map {
    Antonym(it)
}