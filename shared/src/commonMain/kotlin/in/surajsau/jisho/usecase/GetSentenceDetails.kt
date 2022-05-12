package `in`.surajsau.jisho.usecase

import `in`.surajsau.jisho.data.repository.NotesRepository
import `in`.surajsau.jisho.data.repository.SentenceRepository
import `in`.surajsau.jisho.model.NoteResult
import `in`.surajsau.jisho.model.SentenceDetail

internal class GetSentenceDetails constructor(
    private val sentenceRepository: SentenceRepository,
    private val notesRepository: NotesRepository,
) {

    suspend operator fun invoke(id: Long): SentenceDetail {
        val result = sentenceRepository.getSentence(id)
        val note = result.noteId?.let {
            val note = notesRepository.getNote(it)
            return@let NoteResult(id = note.id, text = note.text)
        }

        return SentenceDetail(
            id = result.id,
            japanese = result.jp,
            english = result.en,
            note = note,
        )
    }
}
