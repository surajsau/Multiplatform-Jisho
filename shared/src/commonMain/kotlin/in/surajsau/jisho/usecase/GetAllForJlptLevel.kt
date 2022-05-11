package `in`.surajsau.jisho.usecase

import `in`.surajsau.jisho.data.repository.JlptRepository
import `in`.surajsau.jisho.data.repository.JmdictRepository
import `in`.surajsau.jisho.mapper.mapToSearchResult
import `in`.surajsau.jisho.model.SearchResult

internal class GetAllForJlptLevel(
    private val repository: JlptRepository,
    private val jmdictRepository: JmdictRepository,
) {

    suspend operator fun invoke(level: Int): List<SearchResult> {
        return repository.getForLevel(level = level.toLong())
            .map { it.replace("\n", "") }
            .map { word ->
                val entry = jmdictRepository.getForKanjiOrReading("%$word%")
                entry?.mapToSearchResult(word) ?: throw Exception("Result not found matching $word")
            }
    }
}
