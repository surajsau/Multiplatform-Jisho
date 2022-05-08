package `in`.surajsau.jisho.data.mapper.kanjidic

import `in`.surajsau.jisho.data.model.kanjidic.RadicalName

fun String.radicalNamesFromDb(): List<RadicalName> {
    return split(";").map { RadicalName(it) }
}