package `in`.surajsau.jisho.model.mapper.kanjidic

import `in`.surajsau.jisho.model.kanjidic.RadicalName

public fun String.radicalNamesFromDb(): List<RadicalName> {
    return split(";").map { RadicalName(it) }
}