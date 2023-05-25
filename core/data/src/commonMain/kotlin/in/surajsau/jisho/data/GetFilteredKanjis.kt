package `in`.surajsau.jisho.data

import `in`.surajsau.jisho.data.db.Jisho
import `in`.surajsau.jisho.model.KanjiQuery
import `in`.surajsau.jisho.model.KanjiResult
import `in`.surajsau.jisho.utils.DispatcherProvider
import kotlinx.coroutines.withContext

public class GetFilteredKanjis internal constructor(
    db: Jisho,
    private val dispatcherProvider: DispatcherProvider,
) {
    private val queries = db.kanjiQueries

    public suspend operator fun invoke(query: KanjiQuery): List<KanjiResult> = withContext(dispatcherProvider.io) {
        return@withContext when (query) {
            is KanjiQuery.Freq -> getKanjiWithinFrequency(query.from, query.to)
            is KanjiQuery.Grade -> getKanjiForGrade("${query.grade}")
            is KanjiQuery.AllSchool -> getAllSchoolKanjis()
            is KanjiQuery.All -> getAll()
        }
    }

    private fun getAll(): List<KanjiResult> {
        return queries
            .getAllKanji()
            .executeAsList()
            .map { result ->
                KanjiResult(result.id, result.literal)
            }
    }

    private fun getKanjiWithinFrequency(from: Long, to: Long): List<KanjiResult> {
        return queries
            .getKanjiWithinFreqRange(from, to)
            .executeAsList()
            .map { result ->
                KanjiResult(result.id, result.literal)
            }
    }

    private fun getKanjiForGrade(grade: String): List<KanjiResult> {
        return queries
            .getKanjiForGrade(grade)
            .executeAsList()
            .map { result ->
                KanjiResult(result.id, result.literal)
            }
    }

    private fun getAllSchoolKanjis(): List<KanjiResult> {
        return queries
            .getKanjiWithGrades()
            .executeAsList()
            .map { result ->
                KanjiResult(result.id, result.literal)
            }
    }
}
