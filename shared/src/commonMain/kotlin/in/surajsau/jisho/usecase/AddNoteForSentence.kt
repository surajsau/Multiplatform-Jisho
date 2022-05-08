package `in`.surajsau.jisho.usecase

import `in`.surajsau.jisho.data.repository.NotesRepository
import `in`.surajsau.jisho.data.repository.SentenceRepository

internal class AddNoteForSentence constructor(
    private val repository: NotesRepository,
    private val sentenceRepository: SentenceRepository
) {

    suspend operator fun invoke(id: Long, text: String): Long {
        val noteId = repository.insertNote(text = text)
        sentenceRepository.updateSentenceWithNote(id = id, noteId = noteId)
        return noteId
    }
}