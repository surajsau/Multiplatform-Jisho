package `in`.surajsau.jisho.usecase

import `in`.surajsau.jisho.data.repository.KanjidicRepository
import `in`.surajsau.jisho.model.KanjiQuery
import `in`.surajsau.jisho.model.KanjiResult

class GetFilteredKanjis internal constructor(private val repository: KanjidicRepository) {

    suspend operator fun invoke(query: KanjiQuery): List<KanjiResult> {
        val result = when(query) {
            is KanjiQuery.Freq -> repository.getKanjiWithinFrequency(query.from, query.to)
            is KanjiQuery.Grade -> repository.getKanjiForGrade("${query.grade}")
            is KanjiQuery.AllSchool -> repository.getAllSchoolKanjis()
            is KanjiQuery.All -> repository.getAll()
        }

        return result.map { KanjiResult(it) }
    }
}