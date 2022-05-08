package `in`.surajsau.jisho.data.repository

import `in`.surajsau.jisho.data.model.Sentence

interface SentenceRepository {

    suspend fun getSentencesFor(query: String, limit: Int): List<Sentence>

    suspend fun getAllSentencesFor(query: String): List<Sentence>

    suspend fun getSentence(id: Long): Sentence

    suspend fun updateSentenceWithNote(id: Long, noteId: Long)

    suspend fun getCountOfSentencesFor(query: String): Long
}