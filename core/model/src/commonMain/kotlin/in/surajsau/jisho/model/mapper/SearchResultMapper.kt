package `in`.surajsau.jisho.model.mapper

import `in`.surajsau.jisho.model.SearchResult
import `in`.surajsau.jisho.model.jmdict.JmdictQueryResult
import `in`.surajsau.jisho.model.kanjidic.Kanji
import `in`.surajsau.jisho.model.kanjidic.KanjiQueryResult
import `in`.surajsau.jisho.model.mapper.jmdict.glossFromDb
import `in`.surajsau.jisho.model.mapper.kanjidic.meaningFromDb
import `in`.surajsau.jisho.model.mapper.kanjidic.readingFromDb

public fun JmdictQueryResult.mapToSearchResult(searchTerm: String): SearchResult {
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

    return SearchResult(
        id = "w$id",
        value = value,
        reading = reading,
        meanings = meanings
    )
}

public fun KanjiQueryResult.mapToSearchResult(): SearchResult = SearchResult(
    id = "kq$id",
    value = this.value,
    reading = this.readingString.readingFromDb()
        .filter { (it.type == "ja_on") or (it.type == "ja_kun") }
        .joinToString(", ") { it.value },
    meanings = this.meaningString.meaningFromDb()
        .filter { it.type == "None" }
        .joinToString(", ") { it.value }
)

public fun Kanji.mapToSearchResult(): SearchResult = SearchResult(
    id = "k$id",
    value = this.value,
    reading = this.readings
        .filter { (it.type == "ja_on") or (it.type == "ja_kun") }
        .joinToString(", ") { it.value },
    meanings = this.meanings
        .filter { it.type == "None" }
        .joinToString(", ") { it.value }
)
