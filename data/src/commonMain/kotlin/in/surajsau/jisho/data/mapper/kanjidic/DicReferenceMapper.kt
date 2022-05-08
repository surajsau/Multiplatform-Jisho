package `in`.surajsau.jisho.data.mapper.kanjidic

import `in`.surajsau.jisho.data.model.kanjidic.DicReference

fun String.dicReferencesFromDb(): List<DicReference> {
    return split(";")
        .map {
            val (value, type) = it.split("-")
            DicReference(value, type)
        }
}