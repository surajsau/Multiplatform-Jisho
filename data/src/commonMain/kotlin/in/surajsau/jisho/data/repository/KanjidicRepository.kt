package `in`.surajsau.jisho.data.repository

import `in`.surajsau.jisho.data.model.kanjidic.KanjiQueryResult
import `in`.surajsau.jisho.data.model.kanjidic.Literal

interface KanjidicRepository {
    suspend fun totalCount(): Int

    suspend fun get(literal: String): Literal
    suspend fun getAll(): List<String>
    suspend fun getKanjiWithinFrequency(from: Long, to: Long): List<String>
    suspend fun getKanjiForGrade(grade: String): List<String>

    suspend fun searchForReading(query: String): List<KanjiQueryResult>
}