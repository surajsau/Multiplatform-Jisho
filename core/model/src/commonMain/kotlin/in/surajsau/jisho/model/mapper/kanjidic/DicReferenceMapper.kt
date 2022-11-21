package `in`.surajsau.jisho.model.mapper.kanjidic

import `in`.surajsau.jisho.model.kanjidic.DicReference

public fun String.dicReferencesFromDb(): List<DicReference> {
    return split(";")
        .map {
            val (value, type) = it.split("-")
            DicReference(value, type)
        }
}