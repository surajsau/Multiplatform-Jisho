package `in`.surajsau.jisho.model.mapper.jmdict

import `in`.surajsau.jisho.model.jmdict.Info
import `in`.surajsau.jisho.model.jmdict.Kanji
import `in`.surajsau.jisho.model.jmdict.Priority

public fun String.kanjiFromDb(priorityString: String, infoString: String): List<Kanji> {
    val values = split(";")
    val infos = infoString.split(";")
    val priorites = priorityString.split(";")

    return values.mapIndexed { index, value ->
        Kanji(
            value,
            info = infos[index].split("|").map { Info(it) },
            priority = priorites[index].split("|").map { Priority(it) }
        )
    }
}
