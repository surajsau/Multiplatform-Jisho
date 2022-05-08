package `in`.surajsau.jisho.usecase

import `in`.surajsau.jisho.data.model.kanjidic.Literal
import `in`.surajsau.jisho.data.repository.KanjidicRepository

class GetKanji internal constructor(private val repository: KanjidicRepository) {

    suspend operator fun invoke(literal: String): Literal {
        return repository.get(literal)
    }
}