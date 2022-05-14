package `in`.surajsau.jisho.model

import `in`.surajsau.jisho.data.model.jmdict.JReading
import `in`.surajsau.jisho.data.model.jmdict.Kanji
import `in`.surajsau.jisho.data.model.jmdict.Sense

data class EntryResult(
    val id: Long,
    val kanjis: List<Kanji>,
    val readings: List<JReading>,
    val senses: List<Sense>,
    val note: NoteResult?,
    val bucket: BucketResult?,
)
