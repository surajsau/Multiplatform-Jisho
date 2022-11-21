package `in`.surajsau.jisho.data

import `in`.surajsau.jisho.data.db.Jisho
import `in`.surajsau.jisho.data.expected.DispatcherProvider
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

public class UpdateNotes internal constructor(
    db: Jisho,
    private val dispatcherProvider: DispatcherProvider
) {

    private val queries = db.jishoQueries

    public suspend operator fun invoke(id: Long, text: String): Unit = withContext(dispatcherProvider.io) {
        queries.updateNote(text = text, id = id)
    }
}