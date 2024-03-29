package `in`.surajsau.jisho.data

import `in`.surajsau.jisho.data.db.Jisho
import `in`.surajsau.jisho.model.SearchResult
import `in`.surajsau.jisho.model.jmdict.JmdictQueryResult
import `in`.surajsau.jisho.model.mapper.mapToSearchResult
import `in`.surajsau.jisho.utils.DispatcherProvider
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent

public class SearchForReading internal constructor(
    private val db: Jisho,
    private val dispatcherProvider: DispatcherProvider
): KoinComponent{

    public suspend operator fun invoke(text: String): List<SearchResult> = withContext(dispatcherProvider.io) {
        check(text.isNotEmpty())

        val results = when {
            text.length == 1 -> {
                searchForKanji(query = "$text%") +
                    searchForKanji(query = "%$text")
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
        return db.entryQueries.searchEntryWithKanji(query) { id, keb, re, restr, gloss ->
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
        return db.kanjiQueries.searchKanjiWithReading(query) { id, literal, reading, meaning, _ ->
            JmdictQueryResult(
                id = id,
                kanjiString = literal,
                readingString = reading ?: "",
                glossString = meaning ?: "",
                readingRestrictionString = ""
            )
        }.executeAsList()
    }
}