package `in`.surajsau.jisho.data

import `in`.surajsau.jisho.data.db.Jisho
import `in`.surajsau.jisho.data.expected.DispatcherProvider
import `in`.surajsau.jisho.model.SearchResult
import `in`.surajsau.jisho.model.jmdict.JmdictQueryResult
import `in`.surajsau.jisho.model.kanjidic.KanjiQueryResult
import `in`.surajsau.jisho.model.mapper.mapToSearchResult
import kotlinx.coroutines.withContext

public class GetAllForJlptLevel(
    private val db: Jisho,
    private val dispatcherProvider: DispatcherProvider
) {

    public suspend operator fun invoke(level: Int): List<SearchResult> = withContext(dispatcherProvider.io) {
        val kanjis = getKanjiForJlpt(level.toLong())
        val entries = getEntriesForJlpt(level.toLong())

        return@withContext kanjis.map { it.mapToSearchResult() } +
            entries.map { it.mapToSearchResult("") }
    }

    private fun getKanjiForJlpt(level: Long): List<KanjiQueryResult> {
        return db.kanjiQueries.getKanjiWithJlpt(query = level) { literal, reading, meaning ->
            KanjiQueryResult(literal, reading!!, meaning!!)
        }.executeAsList()
    }

    private fun getEntriesForJlpt(level: Long): List<JmdictQueryResult> {
        return db.entryQueries.getEntriesWithJlpt(query = level) { id, keb, re, restr, gloss ->
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
