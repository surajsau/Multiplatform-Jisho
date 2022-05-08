package `in`.surajsau.jisho.data.mapper.jmdict

import `in`.surajsau.jisho.data.model.jmdict.Kanji
import `in`.surajsau.jisho.data.model.jmdict.Priority

fun String.kanjiFromDb(priorityString: String, infoString: String): List<Kanji> {
    val values = split(";")
    val infos = infoString.split(";")
    val priorites = priorityString.split(";")

    return values.mapIndexed { index, value ->
        Kanji(
            value,
            info = infos[index].split("|").map { `in`.surajsau.jisho.data.model.jmdict.Info(it) },
            priority = priorites[index].split("|").map { Priority(it) }
        )
    }
}