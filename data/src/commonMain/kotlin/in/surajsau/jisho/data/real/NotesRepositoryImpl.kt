package `in`.surajsau.jisho.data.real

import `in`.surajsau.jisho.data.db.Jisho
import `in`.surajsau.jisho.data.expected.DispatcherProvider
import `in`.surajsau.jisho.data.model.Note
import `in`.surajsau.jisho.data.repository.NotesRepository
import kotlinx.coroutines.withContext

class NotesRepositoryImpl constructor(
    jisho: Jisho,
    private val dispatcherProvider: DispatcherProvider,
) : NotesRepository {
    private val queries = jisho.jishoQueries

    override suspend fun updateNotes(id: Long, text: String) = withContext(dispatcherProvider.io) {
        queries.updateNote(text = text, id = id)
    }

    override suspend fun insertNote(text: String): Long =
        withContext(dispatcherProvider.io) {
            queries.insertNote(text = text)
            queries.getLastInsertedNoteId().executeAsOne()
        }

    override suspend fun removeNote(id: Long) = withContext(dispatcherProvider.io) {
        queries.removeNote(id = id)
    }

    override suspend fun getNote(id: Long): Note = withContext(dispatcherProvider.io) {
        return@withContext queries.selectNote(id = id) { id, text -> Note(id, text!!) }.executeAsOne()
    }
}