package `in`.surajsau.jisho.model.mapper.kanjidic

import `in`.surajsau.jisho.model.kanjidic.Nanori

public fun String.nanoriFromDb(): List<Nanori> {
    return split(";").map { Nanori(it) }
}