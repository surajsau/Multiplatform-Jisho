package `in`.surajsau.jisho.data

import `in`.surajsau.jisho.data.db.Jisho
import `in`.surajsau.jisho.data.error.DataNotFoundException
import `in`.surajsau.jisho.data.expected.DispatcherProvider
import `in`.surajsau.jisho.model.kanjidic.Freq
import `in`.surajsau.jisho.model.kanjidic.Grade
import `in`.surajsau.jisho.model.kanjidic.Jlpt
import `in`.surajsau.jisho.model.kanjidic.Literal
import `in`.surajsau.jisho.model.kanjidic.StrokeCount
import `in`.surajsau.jisho.model.mapper.kanjidic.dicReferencesFromDb
import `in`.surajsau.jisho.model.mapper.kanjidic.meaningFromDb
import `in`.surajsau.jisho.model.mapper.kanjidic.nanoriFromDb
import `in`.surajsau.jisho.model.mapper.kanjidic.qCodefromDb
import `in`.surajsau.jisho.model.mapper.kanjidic.radicalFromDb
import `in`.surajsau.jisho.model.mapper.kanjidic.radicalNamesFromDb
import `in`.surajsau.jisho.model.mapper.kanjidic.readingFromDb
import `in`.surajsau.jisho.model.mapper.kanjidic.variantFromDb
import kotlinx.coroutines.withContext

public class GetKanji internal constructor(
    private val db: Jisho,
    private val dispatcherProvider: DispatcherProvider
) {
    public suspend operator fun invoke(literal: String): Literal = withContext(dispatcherProvider.io) {
        val query = db.kanjiQueries.selectKanji(literal = literal)
        val result = query.executeAsOneOrNull() ?: throw DataNotFoundException("$query")

        return@withContext Literal(
            value = result.literal,
            radical = result.radical?.radicalFromDb() ?: emptyList(),
            grade = Grade(result.grade!!),
            jlpt = result.jlpt?.let { Jlpt(it.toInt()) },
            freq = result.freq?.let { Freq(it.toInt()) },
            strokeCount = StrokeCount(result.stroke_count!!.toInt()),
            radicalNames = result.rad_name?.radicalNamesFromDb() ?: emptyList(),
            readings = result.reading?.readingFromDb() ?: emptyList(),
            meanings = result.meaning?.meaningFromDb() ?: emptyList(),
            nanoris = result.nanori?.nanoriFromDb() ?: emptyList(),
            dicReferences = result.dic_number?.dicReferencesFromDb() ?: emptyList(),
            variants = result.variant?.variantFromDb() ?: emptyList(),
            qCodes = result.q_code?.qCodefromDb() ?: emptyList(),
        )
    }
}