package `in`.surajsau.jisho.data

import `in`.surajsau.jisho.data.db.Jisho
import `in`.surajsau.jisho.utils.DispatcherProvider
import `in`.surajsau.jisho.model.NoteResult
import `in`.surajsau.jisho.model.Sentence
import `in`.surajsau.jisho.model.SentenceDetail
import kotlinx.coroutines.withContext

public class GetSentenceDetails constructor(
    private val db: Jisho,
    private val getNote: GetNote,
    private val dispatcherProvider: DispatcherProvider,
) {

    public suspend operator fun invoke(id: Long): SentenceDetail = withContext(dispatcherProvider.io) {
        val result = db.sentenceQueries.getSentence(id) { id, jp, en, noteId ->
            Sentence(id, jp!!.replace("\n", ""), en!!, noteId)
        }.executeAsOne()

        val note = result.noteId?.let {
            val note = getNote(it)
            return@let NoteResult(id = note.id, text = note.text)
        }

        return@withContext SentenceDetail(
            id = result.id,
            japanese = result.jp,
            english = result.en,
            note = note,
        )
    }
}
