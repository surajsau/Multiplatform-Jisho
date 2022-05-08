package `in`.surajsau.jisho.data.real

import `in`.surajsau.jisho.data.db.Jisho
import `in`.surajsau.jisho.data.expected.DispatcherProvider
import `in`.surajsau.jisho.data.mapper.jmdict.kanjiFromDb
import `in`.surajsau.jisho.data.mapper.jmdict.readingFromDb
import `in`.surajsau.jisho.data.mapper.jmdict.senseFromDb
import `in`.surajsau.jisho.data.model.jmdict.Entry
import `in`.surajsau.jisho.data.model.jmdict.JmdictQueryResult
import `in`.surajsau.jisho.data.repository.JmdictRepository
import kotlinx.coroutines.withContext

class JmdictRepositoryImpl constructor(
    db: Jisho,
    private val dispatcherProvider: DispatcherProvider,
) : JmdictRepository {

    private val queries = db.jishoQueries

    override suspend fun searchForKanji(query: String): List<JmdictQueryResult> =
        withContext(dispatcherProvider.io) {
            return@withContext queries.searchEntryWithKanji(query) { id, keb, re, restr, gloss ->
                JmdictQueryResult(
                    id = id,
                    kanjiString = keb ?: "",
                    readingString = re ?: "",
                    glossString = gloss ?: "",
                    readingRestrictionString = restr ?: "",
                )
            }.executeAsList()
        }

    override suspend fun searchForReading(query: String): List<JmdictQueryResult> =
        withContext(dispatcherProvider.io) {
            return@withContext queries.searchEntryWithReading(query) { id, keb, re, restr, gloss ->
                JmdictQueryResult(
                    id = id,
                    kanjiString = keb ?: "",
                    readingString = re ?: "",
                    glossString = gloss ?: "",
                    readingRestrictionString = restr ?: "",
                )
            }.executeAsList()
        }

    override suspend fun getEntry(id: Long): Entry = withContext(dispatcherProvider.io) {
        val result = queries.getEntry(id).executeAsOneOrNull() ?: throw Exception("Entry not found for $id")
        return@withContext Entry(
            id = result.id,
            kanjis = result.keb?.kanjiFromDb(
                priorityString = result.ke_pri ?: "",
                infoString = result.ke_inf ?: ""
            ) ?: emptyList(),
            readings = result.re?.readingFromDb(
                priorityString = result.re_pri ?: "",
                infoString = result.re_inf ?: "",
                restrictionString = result.re_restr ?: "",
                isNotReadingString = result.re_nokanji ?: ""
            ) ?: emptyList(),
            senses = result.gloss?.senseFromDb(
                particleString = result.pos ?: "",
                fieldString = result.field_ ?: "",
                antonymString = result.ant ?: "",
                dialectString = result.dial ?: "",
                exampleString = result.ex_text ?: "",
                exampleSentenceString = result.ex_sent ?: "",
            ) ?: emptyList()
        )
    }

    override suspend fun totalCount(): Long = withContext(dispatcherProvider.io) {
        return@withContext queries.totalEntryCount().executeAsOneOrNull() ?: 0L
    }
}