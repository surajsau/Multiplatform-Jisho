package `in`.surajsau.jisho.mapper

import `in`.surajsau.jisho.data.mapper.jmdict.glossFromDb
import `in`.surajsau.jisho.data.mapper.kanjidic.meaningFromDb
import `in`.surajsau.jisho.data.mapper.kanjidic.readingFromDb
import `in`.surajsau.jisho.data.model.jmdict.JmdictQueryResult
import `in`.surajsau.jisho.data.model.kanjidic.KanjiQueryResult
import `in`.surajsau.jisho.data.model.kanjidic.Literal
import `in`.surajsau.jisho.model.SearchResult

fun JmdictQueryResult.mapToSearchResult(searchTerm: String): SearchResult {
    val id = this.id
    val value = if (this.kanjiString.isEmpty()) {
        this.readingString
    } else {
        val kanjis = this.kanjiString.split(";")
        kanjis.firstOrNull { it.contains(searchTerm) } ?: kanjis.first()
    }

    val reading = if (this.kanjiString.isEmpty()) ""
    else {
        val restrictions = this.readingRestrictionString.split(";")
        val index = restrictions.indexOfFirst { it.contains(value) }
        val readings = this.readingString.split(";")

        readings.getOrNull(index) ?: readings.first()
    }

    val meanings = this.glossString.glossFromDb()
        .joinToString(", ") { it.value }

    return SearchResult(id, value, reading, meanings)
}

fun KanjiQueryResult.mapToSearchResult() = SearchResult(
    id = 0L,
    value = this.value,
    reading = this.readingString.readingFromDb()
        .filter { (it.type == "ja_on") or (it.type == "ja_kun") }
        .joinToString(", ") { it.value },
    meanings = this.meaningString.meaningFromDb()
        .filter { it.type == "None" }
        .joinToString(", ") { it.value }
)

fun Literal.mapToSearchResult() = SearchResult(
    id = 0L,
    value = this.value,
    reading = this.readings
        .filter { (it.type == "ja_on") or (it.type == "ja_kun") }
        .joinToString(", ") { it.value },
    meanings = this.meanings
        .filter { it.type == "None" }
        .joinToString(", ") { it.value }
)
