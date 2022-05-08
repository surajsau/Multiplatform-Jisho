package `in`.surajsau.jisho.usecase

import `in`.surajsau.jisho.data.repository.SentenceRepository
import `in`.surajsau.jisho.model.SentenceQuery
import `in`.surajsau.jisho.model.SentenceResult

internal class GetSentencesForWord constructor(
    private val repository: SentenceRepository
) {
    suspend operator fun invoke(query: SentenceQuery, count: Int): List<SentenceResult> {
        val sentences = when (count) {
            -1 -> repository.getAllSentencesFor(query = query.query)
            else -> repository.getSentencesFor(query = query.query, limit = count)
        }

        return sentences.map { (id, jp, en, _) ->
            SentenceResult(id = id, japanese = jp, english = en)
        }
    }
}