package `in`.surajsau.jisho.data

import `in`.surajsau.jisho.data.db.Jisho
import `in`.surajsau.jisho.data.expected.DispatcherProvider
import kotlinx.coroutines.withContext

public class AddNoteForSentence constructor(
    private val jisho: Jisho,
    private val dispatcherProvider: DispatcherProvider
) {
    public suspend operator fun invoke(id: Long, text: String): Long = withContext(dispatcherProvider.io) {

        jisho.notesQueries.insertNote(text = text)
        val noteId = jisho.notesQueries.getLastInsertedNoteId().executeAsOne()

        jisho.sentenceQueries.updateSentenceWithNote(note_id = noteId, id)

        return@withContext noteId
    }
}