package `in`.surajsau.jisho.usecase

import `in`.surajsau.jisho.data.repository.JlptRepository
import `in`.surajsau.jisho.data.repository.JmdictRepository
import `in`.surajsau.jisho.mapper.mapToSearchResult
import `in`.surajsau.jisho.model.SearchResult
import `in`.surajsau.jisho.utils.Optional

internal class GetAllForJlptLevel(
    private val repository: JlptRepository,
    private val jmdictRepository: JmdictRepository,
) {

    suspend operator fun invoke(level: Int): List<Optional<SearchResult>> {
        return repository.getForLevel(level = level.toLong())
            .map { it.replace("\n", "") }
            .map { word ->
                val entry = jmdictRepository.getForKanjiOrReading("%$word%")
                Optional.of(entry?.mapToSearchResult(word))
            }
    }
}
