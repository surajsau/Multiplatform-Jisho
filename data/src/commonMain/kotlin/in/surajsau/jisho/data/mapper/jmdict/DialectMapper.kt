package `in`.surajsau.jisho.data.mapper.jmdict

import `in`.surajsau.jisho.data.model.jmdict.Dialect

fun String.dialectFromDb() = split("|").map { Dialect(it) }