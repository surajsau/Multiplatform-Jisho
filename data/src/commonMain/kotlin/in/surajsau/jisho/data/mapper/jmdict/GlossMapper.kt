package `in`.surajsau.jisho.data.mapper.jmdict

import `in`.surajsau.jisho.data.model.jmdict.Gloss

fun String.glossFromDb(): List<Gloss> {
    return split("|").map {
        val (value, type) = it.split("-")
        Gloss(
            value,
            type = if (type.isEmpty()) null else `in`.surajsau.jisho.data.model.jmdict.Info(type)
        )
    }
}