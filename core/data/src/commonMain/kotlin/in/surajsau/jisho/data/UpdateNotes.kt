package `in`.surajsau.jisho.data

import `in`.surajsau.jisho.data.db.Jisho
import `in`.surajsau.jisho.data.expected.DispatcherProvider
import kotlinx.coroutines.withContext

public class UpdateNotes internal constructor(
    private val db: Jisho,
    private val dispatcherProvider: DispatcherProvider
) {
    public suspend operator fun invoke(id: Long, text: String): Unit = withContext(dispatcherProvider.io) {
        db.notesQueries.updateNote(text = text, id = id)
    }
}