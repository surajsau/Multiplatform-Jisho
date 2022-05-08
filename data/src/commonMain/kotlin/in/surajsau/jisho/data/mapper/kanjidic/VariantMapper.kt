package `in`.surajsau.jisho.data.mapper.kanjidic

import `in`.surajsau.jisho.data.model.kanjidic.Variant

fun String.variantFromDb(): List<Variant> {
    return split(";")
        .filterNot { it.isEmpty() }
        .map {
            val (value, type) = it.split("-")
            Variant(value, type)
        }
}