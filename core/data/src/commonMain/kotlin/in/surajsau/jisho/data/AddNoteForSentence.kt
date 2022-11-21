package `in`.surajsau.jisho.data

import `in`.surajsau.jisho.data.db.Jisho
import `in`.surajsau.jisho.data.expected.DispatcherProvider
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

public class AddNoteForSentence constructor(
    private val jisho: Jisho,
    private val dispatcherProvider: DispatcherProvider
) {
    public suspend operator fun invoke(id: Long, text: String): Long = withContext(dispatcherProvider.io) {
        val queries = jisho.jishoQueries

        queries.insertNote(text = text)
        val noteId = queries.getLastInsertedNoteId().executeAsOne()

        queries.updateSentenceWithNote(note_id = noteId, id)

        return@withContext noteId
    }
}