package `in`.surajsau.jisho.usecase

import `in`.surajsau.jisho.data.repository.NotesRepository
import `in`.surajsau.jisho.model.NoteResult

internal class GetNote constructor(private val repository: NotesRepository) {

    suspend operator fun invoke(id: Long): NoteResult {
        val result = repository.getNote(id)
        return NoteResult(id = result.id, text = result.text)
    }
}