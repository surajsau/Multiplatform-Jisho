package `in`.surajsau.jisho.data.repository

import `in`.surajsau.jisho.data.model.Conjugation

interface ConjugationRepository {

    suspend fun getConjugationsFor(pos: Long): List<Conjugation>
}
