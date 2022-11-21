package `in`.surajsau.jisho.model.mapper.jmdict

import `in`.surajsau.jisho.model.jmdict.Gloss
import `in`.surajsau.jisho.model.jmdict.Info

public fun String.glossFromDb(): List<Gloss> {
    return split("|").map {
        val (value, type) = it.split("-")
        Gloss(
            value,
            type = if (type.isEmpty()) null else Info(type)
        )
    }
}