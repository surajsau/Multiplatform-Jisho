package `in`.surajsau.jisho.data.repository

import `in`.surajsau.jisho.data.model.Note

interface NotesRepository {

    suspend fun updateNotes(id: Long, text: String)
    suspend fun insertNote(text: String): Long
    suspend fun removeNote(id: Long)
    suspend fun getNote(id: Long): Note
}