package `in`.surajsau.jisho.model.mapper.kanjidic

import `in`.surajsau.jisho.model.kanjidic.Variant

public fun String.variantFromDb(): List<Variant> {
    return split(";")
        .filterNot { it.isEmpty() }
        .map {
            val (value, type) = it.split("-")
            Variant(value, type)
        }
}