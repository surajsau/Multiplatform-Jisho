package `in`.surajsau.jisho.usecase

import `in`.surajsau.jisho.data.repository.NotesRepository

internal class RemoveNote constructor(private val repository: NotesRepository) {

    suspend operator fun invoke(id: Long) {
        repository.removeNote(id)
    }
}