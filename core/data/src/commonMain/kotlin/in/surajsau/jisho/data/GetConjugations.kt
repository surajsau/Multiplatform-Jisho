package `in`.surajsau.jisho.data

import `in`.surajsau.jisho.data.db.Jisho
import `in`.surajsau.jisho.data.expected.DispatcherProvider
import `in`.surajsau.jisho.model.Conjugation
import `in`.surajsau.jisho.model.jmdict.Info
import `in`.surajsau.jisho.model.ConjugationResult
import kotlinx.coroutines.withContext

public class GetConjugations(
    private val db: Jisho,
    private val dispatcherProvider: DispatcherProvider,
) {

    public suspend operator fun invoke(word: String, pos: Info): ConjugationResult? = withContext(dispatcherProvider.io) {
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
            return@withContext null
        }

        val result = db.jishoQueries.getConjugationsForPos(pos = posKey.toLong()).executeAsList()
        val conjugations = result.map {
            Conjugation(
                conj = it.conj!!,
                isFormal = it.formal == "t",
                isNegative = it.negative == "t",
                onum = it.onum!!.toInt(),
                stem = it.stem!!.toInt(),
                okurigana = it.okurigana!!,
                euphk = it.euphk,
                euphr = it.euphr
            )
        }

        return@withContext when (posKey) {
            1, 7, 15 -> ConjugationResult.Adjective(word, conjugations)
            else -> ConjugationResult.Verb(word, conjugations)
        }
    }
}
