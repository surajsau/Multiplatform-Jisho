package `in`.surajsau.jisho.data

import `in`.surajsau.jisho.data.db.Jisho
import `in`.surajsau.jisho.utils.DispatcherProvider
import `in`.surajsau.jisho.model.SearchResult
import `in`.surajsau.jisho.model.jmdict.JmdictQueryResult
import `in`.surajsau.jisho.model.mapper.mapToSearchResult
import kotlinx.coroutines.withContext

public class SearchForKanji internal constructor(
    private val db: Jisho,
    private val dispatcherProvider: DispatcherProvider,
    private val getKanji: GetKanji
) {
    @Throws(IllegalStateException::class)
    public suspend operator fun invoke(text: String): List<SearchResult> = withContext(dispatcherProvider.io) {
        check(text.isNotEmpty())

        val results = when {
            text.length == 1 -> {
                searchForKanji(query = "$text%") +
                    searchForKanji(query = "%$text")
            }

            else -> searchForKanji(query = "$text%")
        }.map { it.mapToSearchResult(text) }

        val kanjiResults = when (text.length) {
            1 -> listOf(getKanji(literal = text))
            else -> emptyList()
        }.map { it.mapToSearchResult() }

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
}