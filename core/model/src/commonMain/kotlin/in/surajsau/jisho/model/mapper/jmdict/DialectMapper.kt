package `in`.surajsau.jisho.model.mapper.jmdict

import `in`.surajsau.jisho.model.jmdict.Dialect

public fun String.dialectFromDb(): List<Dialect> = split("|").map { Dialect(it) }