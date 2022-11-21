package `in`.surajsau.jisho.data

import `in`.surajsau.jisho.data.db.Jisho
import `in`.surajsau.jisho.data.expected.DispatcherProvider
import kotlinx.coroutines.withContext

public class RemoveNote internal constructor(
    db: Jisho,
    private val dispatcherProvider: DispatcherProvider
){
    private val queries = db.jishoQueries

    public suspend operator fun invoke(id: Long) {
        withContext(dispatcherProvider.io) {
            queries.removeNote(id)
        }
    }
}