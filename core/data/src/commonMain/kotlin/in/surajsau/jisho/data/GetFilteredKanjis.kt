package `in`.surajsau.jisho.data

import `in`.surajsau.jisho.data.db.Jisho
import `in`.surajsau.jisho.model.KanjiQuery
import `in`.surajsau.jisho.model.KanjiResult
import `in`.surajsau.jisho.utils.DispatcherProvider
import kotlinx.coroutines.withContext

public class GetFilteredKanjis internal constructor(
    private val db: Jisho,
    private val dispatcherProvider: DispatcherProvider,
) {
    public suspend operator fun invoke(query: KanjiQuery): List<KanjiResult> = withContext(dispatcherProvider.io) {
        val result = when (query) {
            is KanjiQuery.Freq -> getKanjiWithinFrequency(query.from, query.to)
            is KanjiQuery.Grade -> getKanjiForGrade("${query.grade}")
            is KanjiQuery.AllSchool -> getAllSchoolKanjis()
            is KanjiQuery.All -> getAll()
        }

        return@withContext result.map { KanjiResult(it) }
    }

    private fun getAll(): List<String> {
        return db.kanjiQueries.getAllKanji().executeAsList().map { it.literal }
    }

    private fun getKanjiWithinFrequency(from: Long, to: Long): List<String> {
        return db.kanjiQueries.getKanjiWithinFreqRange(from, to).executeAsList().map { it.literal }
    }

    private fun getKanjiForGrade(grade: String): List<String> {
        return db.kanjiQueries.getKanjiForGrade(grade).executeAsList().map { it.literal }
    }

    private fun getAllSchoolKanjis(): List<String> {
        return db.kanjiQueries.getKanjiWithGrades().executeAsList().map { it.literal }
    }
}
