package `in`.surajsau.jisho.data

import `in`.surajsau.jisho.data.db.Jisho
import `in`.surajsau.jisho.utils.DispatcherProvider
import `in`.surajsau.jisho.model.KanjiQuery
import `in`.surajsau.jisho.model.KanjiQueryAll
import `in`.surajsau.jisho.model.KanjiQueryAllSchool
import `in`.surajsau.jisho.model.KanjiQueryFreq
import `in`.surajsau.jisho.model.KanjiQueryGrade
import `in`.surajsau.jisho.model.KanjiResult
import kotlinx.coroutines.withContext

public class GetFilteredKanjis internal constructor(
    private val db: Jisho,
    private val dispatcherProvider: DispatcherProvider,
) {
    public suspend operator fun invoke(query: KanjiQuery): List<KanjiResult> = withContext(dispatcherProvider.io) {
        val result = when (query) {
            is KanjiQueryFreq -> getKanjiWithinFrequency(query.from, query.to)
            is KanjiQueryGrade -> getKanjiForGrade("${query.grade}")
            is KanjiQueryAllSchool -> getAllSchoolKanjis()
            is KanjiQueryAll -> getAll()
        }

        return@withContext result.map { KanjiResult(it) }
    }

    private fun getAll(): List<String> {
        return db.kanjiQueries.getAllKanji().executeAsList()
    }

    private fun getKanjiWithinFrequency(from: Long, to: Long): List<String> {
        return db.kanjiQueries.getKanjiWithinFreqRange(from, to).executeAsList()
    }

    private fun getKanjiForGrade(grade: String): List<String> {
        return db.kanjiQueries.getKanjiForGrade(grade).executeAsList()
    }

    private fun getAllSchoolKanjis(): List<String> {
        return db.kanjiQueries.getKanjiWithGrades().executeAsList()
    }
}
