package `in`.surajsau.jisho.model.mapper.jmdict

import `in`.surajsau.jisho.model.jmdict.Gloss
import `in`.surajsau.jisho.model.jmdict.Info

public fun String.glossFromDb(): List<Gloss> {
    return runCatching {
        split("|").map {
            val (value, type) = it.split("-")
            Gloss(
                value = value,
                type = if (type.isEmpty()) null else Info(type)
            )
        }
    }.getOrElse { emptyList() }
}