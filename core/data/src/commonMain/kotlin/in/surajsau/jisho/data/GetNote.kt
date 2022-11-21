package `in`.surajsau.jisho.data

import `in`.surajsau.jisho.data.db.Jisho
import `in`.surajsau.jisho.data.expected.DispatcherProvider
import `in`.surajsau.jisho.model.Note
import `in`.surajsau.jisho.model.NoteResult
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

public class GetNote internal constructor(
    db: Jisho,
    private val dispatcherProvider: DispatcherProvider
){
    private val queries = db.jishoQueries

    public suspend operator fun invoke(id: Long): NoteResult = withContext(dispatcherProvider.io) {
        val result = queries.selectNote(id = id) { id, text -> Note(id, text!!) }.executeAsOne()
        return@withContext NoteResult(id = result.id, text = result.text)
    }
}