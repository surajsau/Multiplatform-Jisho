package `in`.surajsau.jisho.data.mapper.kanjidic

import `in`.surajsau.jisho.data.model.kanjidic.Nanori

fun String.nanoriFromDb(): List<Nanori> {
    return split(";").map { Nanori(it) }
}