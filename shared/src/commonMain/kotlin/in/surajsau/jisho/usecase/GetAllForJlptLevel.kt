package `in`.surajsau.jisho.usecase

import `in`.surajsau.jisho.data.repository.JmdictRepository
import `in`.surajsau.jisho.data.repository.KanjidicRepository
import `in`.surajsau.jisho.mapper.mapToSearchResult
import `in`.surajsau.jisho.model.SearchResult

internal class GetAllForJlptLevel(
    private val kanjidicRepository: KanjidicRepository,
    private val jmdictRepository: JmdictRepository,
) {

    suspend operator fun invoke(level: Int): List<SearchResult> {
        val kanjis = kanjidicRepository.getKanjiForJlpt(level.toLong())
        val entries = jmdictRepository.getEntriesForJlpt(level.toLong())

        return kanjis.map { it.mapToSearchResult() } +
            entries.map { it.mapToSearchResult("") }
    }
}
