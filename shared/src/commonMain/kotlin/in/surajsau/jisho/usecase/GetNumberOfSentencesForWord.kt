package `in`.surajsau.jisho.usecase

import `in`.surajsau.jisho.data.repository.SentenceRepository
import `in`.surajsau.jisho.model.SentenceQuery

internal class GetNumberOfSentencesForWord constructor(
    private val repository: SentenceRepository
) {

    suspend operator fun invoke(query: SentenceQuery): Int {
        return repository.getCountOfSentencesFor(query = query.query).toInt()
    }
}
