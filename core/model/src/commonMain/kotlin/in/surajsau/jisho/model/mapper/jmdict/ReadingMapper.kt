package `in`.surajsau.jisho.model.mapper.jmdict

import `in`.surajsau.jisho.model.jmdict.Info
import `in`.surajsau.jisho.model.jmdict.JReading
import `in`.surajsau.jisho.model.jmdict.Priority
import `in`.surajsau.jisho.model.jmdict.Restriction

public fun String.readingFromDb(
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
            info = infos[index].split("|").map { Info(it) },
            priority = priorities[index].split("|").map { Priority(it) },
            isNotTrueReading = isNotReadings[index] == "T",
            restriction = restrictions[index].split("|").map { Restriction(it) }
        )
    }
}
