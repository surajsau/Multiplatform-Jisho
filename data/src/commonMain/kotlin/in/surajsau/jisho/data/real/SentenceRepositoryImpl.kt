package `in`.surajsau.jisho.data.real

import `in`.surajsau.jisho.data.db.Jisho
import `in`.surajsau.jisho.data.expected.DispatcherProvider
import `in`.surajsau.jisho.data.model.Sentence
import `in`.surajsau.jisho.data.repository.SentenceRepository
import kotlinx.coroutines.withContext

class SentenceRepositoryImpl constructor(
    jisho: Jisho,
    private val dispatcherProvider: DispatcherProvider,
): SentenceRepository {

    private val queries = jisho.jishoQueries

    override suspend fun getSentencesFor(query: String, limit: Int): List<Sentence> = withContext(dispatcherProvider.io) {
        return@withContext queries.getLimitedSentencesWithKeyword(query, limit.toLong()) { id, jp, en ->
            Sentence(id, jp!!.replace("\n", ""), en!!, null)
        }.executeAsList()
    }

    override suspend fun getAllSentencesFor(query: String): List<Sentence> = withContext(dispatcherProvider.io) {
        return@withContext queries.getAllSentencesWithKeyword(query) { id, jp, en ->
            Sentence(id, jp!!.replace("\n", ""), en!!, null)
        }.executeAsList()
    }

    override suspend fun getSentence(id: Long): Sentence = withContext(dispatcherProvider.io) {
        return@withContext queries.getSentence(id) { id, jp, en, noteId ->
            Sentence(id, jp!!.replace("\n", ""), en!!, noteId)
        }.executeAsOne()
    }

    override suspend fun updateSentenceWithNote(id: Long, noteId: Long) = withContext(dispatcherProvider.io) {
        queries.updateSentenceWithNote(note_id = noteId, id)
    }

    override suspend fun getCountOfSentencesFor(query: String): Long = withContext(dispatcherProvider.io) {
        return@withContext queries.getCountOfSentencesWithKeyword(query).executeAsOne()
    }

}