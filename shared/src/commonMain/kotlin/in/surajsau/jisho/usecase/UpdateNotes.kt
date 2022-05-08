package `in`.surajsau.jisho.usecase

import `in`.surajsau.jisho.data.repository.NotesRepository

internal class UpdateNotes constructor(private val repository: NotesRepository) {

    suspend operator fun invoke(id: Long, text: String) {
        repository.updateNotes(id, text)
    }
}