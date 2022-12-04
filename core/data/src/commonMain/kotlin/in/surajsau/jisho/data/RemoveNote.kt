package `in`.surajsau.jisho.data

import `in`.surajsau.jisho.data.db.Jisho
import `in`.surajsau.jisho.data.expected.DispatcherProvider
import kotlinx.coroutines.withContext

public class RemoveNote internal constructor(
    private val db: Jisho,
    private val dispatcherProvider: DispatcherProvider
){
    public suspend operator fun invoke(id: Long) {
        withContext(dispatcherProvider.io) {
            db.notesQueries.removeNote(id)
        }
    }
}