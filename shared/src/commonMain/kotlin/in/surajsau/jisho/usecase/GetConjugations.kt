package `in`.surajsau.jisho.usecase

import `in`.surajsau.jisho.data.model.jmdict.Info
import `in`.surajsau.jisho.data.repository.ConjugationRepository
import `in`.surajsau.jisho.model.ConjugationResult

internal class GetConjugations(private val repository: ConjugationRepository) {

    suspend operator fun invoke(word: String, pos: Info): ConjugationResult? {
        val posKey = when (pos.value) {
            "adj-i" -> 1
            "adj-ix" -> 7
            "cop" -> 15
            "v1" -> 28
            "v1-s" -> 29
            "v5aru" -> 30
            "v5b" -> 31
            "v5g" -> 32
            "v5k" -> 33
            "v5k-s" -> 34
            "v5m" -> 35
            "v5n" -> 36
            "v5r" -> 37
            "v5r-i" -> 38
            "v5s" -> 39
            "v5t" -> 40
            "v5u" -> 41
            "v5u-s" -> 42
            "v5uru" -> 43
            "vi" -> 44
            "vk" -> 45
            "vs-s" -> 47
            "vs-i" -> 48

            else -> -1
        }

        if (posKey == -1) {
            return null
        }

        val conjugations = repository.getConjugationsFor(posKey.toLong())
        return when (posKey) {
            1, 7, 15 -> ConjugationResult.Adjective(word, conjugations)
            else -> ConjugationResult.Verb(word, conjugations)
        }
    }
}
