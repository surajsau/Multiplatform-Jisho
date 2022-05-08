package `in`.surajsau.jisho.data.mapper.jmdict

import `in`.surajsau.jisho.data.model.jmdict.JReading
import `in`.surajsau.jisho.data.model.jmdict.Priority
import `in`.surajsau.jisho.data.model.jmdict.Restriction

fun String.readingFromDb(
    infoString: String,
    priorityString: String,
    isNotReadingString: String,
    restrictionString: String
): List<JReading> {
    val infos = infoString.split(";")
    val priorities = priorityString.split(";")
    val isNotReadings = isNotReadingString.split(";")
    val restrictions = restrictionString.split(";")

    return split(";").mapIndexed { index, value ->
        JReading(
            value = value,
            info = infos[index].split("|").map { `in`.surajsau.jisho.data.model.jmdict.Info(it) },
            priority = priorities[index].split("|").map { Priority(it) },
            isNotTrueReading = isNotReadings[index] == "T",
            restriction = restrictions[index].split("|").map { Restriction(it) }
        )
    }
}
