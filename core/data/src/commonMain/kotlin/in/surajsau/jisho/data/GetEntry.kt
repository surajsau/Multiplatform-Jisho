package `in`.surajsau.jisho.data

import `in`.surajsau.jisho.data.db.Jisho
import `in`.surajsau.jisho.data.expected.DispatcherProvider
import `in`.surajsau.jisho.model.jmdict.Entry
import `in`.surajsau.jisho.model.mapper.jmdict.kanjiFromDb
import `in`.surajsau.jisho.model.mapper.jmdict.readingFromDb
import `in`.surajsau.jisho.model.mapper.jmdict.senseFromDb
import kotlinx.coroutines.withContext

public class GetEntry internal constructor(
    private val db: Jisho,
    private val dispatcherProvider: DispatcherProvider
) {

    public suspend operator fun invoke(id: Long): Entry = withContext(dispatcherProvider.io) {
        val result = db.jishoQueries.getEntry(id).executeAsOneOrNull() ?: throw Exception("Entry not found for $id")
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
}