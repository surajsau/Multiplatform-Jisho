package `in`.surajsau.jisho.usecase

import `in`.surajsau.jisho.data.repository.JmdictRepository
import `in`.surajsau.jisho.data.repository.KanjidicRepository
import `in`.surajsau.jisho.mapper.mapToSearchResult
import `in`.surajsau.jisho.model.SearchResult

internal class SearchForReading constructor(
    private val jmdictRepository: JmdictRepository,
    private val kanjidicRepository: KanjidicRepository,
) {

    suspend operator fun invoke(text: String): List<SearchResult> {
        check(text.isNotEmpty())

        val results = when {
            text.length == 1 -> {
                jmdictRepository.searchForKanji(query = "$text%") +
                    jmdictRepository.searchForKanji(query = "%$text")
            }

            else -> jmdictRepository.searchForKanji(query = "$text%")
        }.map { it.mapToSearchResult(text) }

        val kanjiResults = when {
            text.length == 1 -> { kanjidicRepository.searchForReading(query = "%;$text%") }
            else -> emptyList()
        }.map { it.mapToSearchResult() }

        return kanjiResults + results
    }
}