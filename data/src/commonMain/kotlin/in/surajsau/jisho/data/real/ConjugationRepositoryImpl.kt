package `in`.surajsau.jisho.data.real

import `in`.surajsau.jisho.data.db.Jisho
import `in`.surajsau.jisho.data.expected.DispatcherProvider
import `in`.surajsau.jisho.data.model.Conjugation
import `in`.surajsau.jisho.data.repository.ConjugationRepository
import kotlinx.coroutines.withContext

class ConjugationRepositoryImpl constructor(
    jisho: Jisho,
    private val dispatcherProvider: DispatcherProvider
) : ConjugationRepository {

    private val queries = jisho.jishoQueries

    override suspend fun getConjugationsFor(pos: Long): List<Conjugation> = withContext(dispatcherProvider.io) {
        val result = queries.getConjugationsForPos(pos = pos).executeAsList()
        return@withContext result.map {
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
    }
}
