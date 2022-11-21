package `in`.surajsau.jisho.data

import `in`.surajsau.jisho.data.db.Jisho
import `in`.surajsau.jisho.data.expected.DispatcherProvider
import `in`.surajsau.jisho.model.SearchResult
import `in`.surajsau.jisho.model.jmdict.JmdictQueryResult
import `in`.surajsau.jisho.model.mapper.mapToSearchResult
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

public class SearchForReading internal constructor(
    db: Jisho,
    private val dispatcherProvider: DispatcherProvider
): KoinComponent{

    private val queries = db.jishoQueries

    public suspend operator fun invoke(text: String): List<SearchResult> = withContext(dispatcherProvider.io) {
        check(text.isNotEmpty())

        val results = when {
            text.length == 1 -> {
                searchForKanji(query = "$text%") + searchForKanji(query = "%$text")
            }

            else -> searchForKanji(query = "$text%")
        }.map { it.mapToSearchResult(text) }

        val kanjiResults = when {
            text.length == 1 -> { searchForReading(query = "%;$text%") }
            else -> emptyList()
        }.map { it.mapToSearchResult(text) }

        return@withContext kanjiResults + results
    }

    private fun searchForKanji(query: String): List<JmdictQueryResult> {
        return queries.searchEntryWithKanji(query) { id, keb, re, restr, gloss ->
            JmdictQueryResult(
                id = id,
                kanjiString = keb ?: "",
                readingString = re ?: "",
                glossString = gloss ?: "",
                readingRestrictionString = restr ?: "",
            )
        }.executeAsList()
    }

    private fun searchForReading(query: String): List<JmdictQueryResult> {
        return queries.searchKanjiWithReading(query) { literal, reading, meaning, _ ->
            JmdictQueryResult(
                id = 0L,
                kanjiString = literal,
                readingString = reading ?: "",
                glossString = meaning ?: "",
                readingRestrictionString = ""
            )
        }.executeAsList()
    }
}