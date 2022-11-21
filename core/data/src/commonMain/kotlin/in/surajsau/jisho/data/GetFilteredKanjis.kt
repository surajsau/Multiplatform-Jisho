package `in`.surajsau.jisho.data

import `in`.surajsau.jisho.data.db.Jisho
import `in`.surajsau.jisho.data.expected.DispatcherProvider
import `in`.surajsau.jisho.model.KanjiQuery
import `in`.surajsau.jisho.model.KanjiResult
import kotlinx.coroutines.withContext

public class GetFilteredKanjis internal constructor(
    db: Jisho,
    private val dispatcherProvider: DispatcherProvider,
) {

    private val queries = db.jishoQueries

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
        return queries.getAllKanji().executeAsList()
    }

    private fun getKanjiWithinFrequency(from: Long, to: Long): List<String> {
        return queries.getKanjiWithinFreqRange(from, to).executeAsList()
    }

    private fun getKanjiForGrade(grade: String): List<String> {
        return queries.getKanjiForGrade(grade).executeAsList()
    }

    private fun getAllSchoolKanjis(): List<String> {
        return queries.getKanjiWithGrades().executeAsList()
    }
}
