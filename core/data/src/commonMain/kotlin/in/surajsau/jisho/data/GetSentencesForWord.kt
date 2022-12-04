package `in`.surajsau.jisho.data

import `in`.surajsau.jisho.data.db.Jisho
import `in`.surajsau.jisho.data.expected.DispatcherProvider
import `in`.surajsau.jisho.model.Sentence
import `in`.surajsau.jisho.model.SentenceQuery
import `in`.surajsau.jisho.model.SentenceResult
import kotlinx.coroutines.withContext

public class GetSentencesForWord constructor(
    private val db: Jisho,
    private val dispatcherProvider: DispatcherProvider
) {

    public suspend operator fun invoke(query: SentenceQuery, count: Int): List<SentenceResult> = withContext(dispatcherProvider.io) {
        val sentences = when (count) {
            -1 -> getAllSentencesFor(query = query.query)
            else -> getSentencesFor(query = query.query, limit = count)
        }

        return@withContext sentences.map { (id, jp, en, _) ->
            SentenceResult(id = id, japanese = jp, english = en)
        }
    }

    private fun getAllSentencesFor(query: String): List<Sentence> {
        return db.sentenceQueries.getAllSentencesWithKeyword(query) { id, jp, en ->
            Sentence(id, jp!!.replace("\n", ""), en!!, null)
        }.executeAsList()
    }

    private fun getSentencesFor(query: String, limit: Int): List<Sentence> {
        return db.sentenceQueries.getLimitedSentencesWithKeyword(query, limit.toLong()) { id, jp, en ->
            Sentence(id, jp!!.replace("\n", ""), en!!, null)
        }.executeAsList()
    }
}