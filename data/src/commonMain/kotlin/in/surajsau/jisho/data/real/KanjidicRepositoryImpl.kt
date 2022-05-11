package `in`.surajsau.jisho.data.real

import `in`.surajsau.jisho.data.db.Jisho
import `in`.surajsau.jisho.data.error.DataNotFoundException
import `in`.surajsau.jisho.data.expected.DispatcherProvider
import `in`.surajsau.jisho.data.mapper.kanjidic.dicReferencesFromDb
import `in`.surajsau.jisho.data.mapper.kanjidic.meaningFromDb
import `in`.surajsau.jisho.data.mapper.kanjidic.nanoriFromDb
import `in`.surajsau.jisho.data.mapper.kanjidic.qCodefromDb
import `in`.surajsau.jisho.data.mapper.kanjidic.radicalFromDb
import `in`.surajsau.jisho.data.mapper.kanjidic.radicalNamesFromDb
import `in`.surajsau.jisho.data.mapper.kanjidic.readingFromDb
import `in`.surajsau.jisho.data.mapper.kanjidic.variantFromDb
import `in`.surajsau.jisho.data.model.kanjidic.Freq
import `in`.surajsau.jisho.data.model.kanjidic.Grade
import `in`.surajsau.jisho.data.model.kanjidic.Jlpt
import `in`.surajsau.jisho.data.model.kanjidic.KanjiQueryResult
import `in`.surajsau.jisho.data.model.kanjidic.Literal
import `in`.surajsau.jisho.data.model.kanjidic.StrokeCount
import `in`.surajsau.jisho.data.repository.KanjidicRepository
import kotlinx.coroutines.withContext

class KanjidicRepositoryImpl constructor(
    jisho: Jisho,
    private val dispatcherProvider: DispatcherProvider,
) : KanjidicRepository {

    private val queries = jisho.jishoQueries

    override suspend fun totalCount(): Int = withContext(dispatcherProvider.io) {
        return@withContext queries.totalKanjiCount().executeAsOneOrNull()?.toInt() ?: 0
    }

    override suspend fun getAll(): List<String> = withContext(dispatcherProvider.io) {
        return@withContext queries.getAllKanji().executeAsList()
    }

    override suspend fun getKanjiWithinFrequency(from: Long, to: Long): List<String> = withContext(dispatcherProvider.io) {
        return@withContext queries.getKanjiWithinFreqRange(from, to).executeAsList()
    }

    override suspend fun getKanjiForGrade(grade: String): List<String> = withContext(dispatcherProvider.io) {
        return@withContext queries.getKanjiForGrade(grade).executeAsList()
    }

    override suspend fun getAllSchoolKanjis(): List<String> = withContext(dispatcherProvider.io) {
        return@withContext queries.getKanjiWithGrades().executeAsList()
    }

    override suspend fun searchForReading(query: String): List<KanjiQueryResult> = withContext(dispatcherProvider.io) {
        queries.searchKanjiWithReading(query).executeAsList()
        return@withContext emptyList<KanjiQueryResult>()
    }

    override suspend fun getKanjiForJlpt(level: Long): List<KanjiQueryResult> = withContext(dispatcherProvider.io) {
        return@withContext queries.getKanjiWithJlpt(query = level) { literal, reading, meaning ->
            KanjiQueryResult(literal, reading!!, meaning!!)
        }.executeAsList()
    }

    override suspend fun get(literal: String) = withContext(dispatcherProvider.io) {
        val query = queries.selectKanji(literal = literal)
        val result = query.executeAsOneOrNull() ?: throw DataNotFoundException("$query")

        return@withContext Literal(
            value = result.literal,
            radical = result.radical?.radicalFromDb() ?: emptyList(),
            grade = Grade(result.grade!!),
            jlpt = result.jlpt?.let { Jlpt(it.toInt()) },
            freq = result.freq?.let { Freq(it.toInt()) },
            strokeCount = StrokeCount(result.stroke_count!!.toInt()),
            radicalNames = result.rad_name?.radicalNamesFromDb() ?: emptyList(),
            readings = result.reading?.readingFromDb() ?: emptyList(),
            meanings = result.meaning?.meaningFromDb() ?: emptyList(),
            nanoris = result.nanori?.nanoriFromDb() ?: emptyList(),
            dicReferences = result.dic_number?.dicReferencesFromDb() ?: emptyList(),
            variants = result.variant?.variantFromDb() ?: emptyList(),
            qCodes = result.q_code?.qCodefromDb() ?: emptyList(),
        )
    }
}