package `in`.surajsau.jisho.data.repository

import `in`.surajsau.jisho.data.model.jmdict.Entry
import `in`.surajsau.jisho.data.model.jmdict.JmdictQueryResult

interface JmdictRepository {
    suspend fun searchForKanji(query: String): List<JmdictQueryResult>
    suspend fun searchForReading(query: String): List<JmdictQueryResult>

    suspend fun getEntry(id: Long): Entry

    suspend fun totalCount(): Long
}
