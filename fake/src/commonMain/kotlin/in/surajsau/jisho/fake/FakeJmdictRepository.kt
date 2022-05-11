package `in`.surajsau.jisho.fake

import `in`.surajsau.jisho.data.model.jmdict.Entry
import `in`.surajsau.jisho.data.model.jmdict.Gloss
import `in`.surajsau.jisho.data.model.jmdict.Info
import `in`.surajsau.jisho.data.model.jmdict.JReading
import `in`.surajsau.jisho.data.model.jmdict.JmdictQueryResult
import `in`.surajsau.jisho.data.model.jmdict.Kanji
import `in`.surajsau.jisho.data.model.jmdict.Sense
import `in`.surajsau.jisho.data.repository.JmdictRepository

class FakeJmdictRepository : JmdictRepository {

    override suspend fun searchForKanji(query: String): List<JmdictQueryResult> {
        return Entries.filter { entry ->
            entry.kanjis.any { kanji -> kanji.value.contains(convertQueryToRegex(text = query)) }
        }
            .map { entry ->
                JmdictQueryResult(
                    id = entry.id,
                    kanjiString = entry.kanjis.joinToString(";") { it.value },
                    readingString = entry.readings.joinToString(";") { it.value },
                    glossString = entry.senses.joinToString(";") { sense ->
                        sense.glosses.joinToString("|") { gloss -> "${gloss.value}-${gloss.type ?: ""}" }
                    },
                    readingRestrictionString = entry.readings.joinToString(";") { it.restriction.joinToString("|") }
                )
            }
    }

    override suspend fun searchForReading(query: String): List<JmdictQueryResult> {
        return Entries.filter { entry ->
            entry.readings.any { reading -> reading.value.contains(convertQueryToRegex(text = query)) }
        }
            .map { entry ->
                JmdictQueryResult(
                    id = entry.id,
                    kanjiString = entry.kanjis.joinToString(";") { it.value },
                    readingString = entry.readings.joinToString(";") { it.value },
                    glossString = entry.senses.joinToString(";") { sense ->
                        sense.glosses.joinToString("|") { gloss -> "${gloss.value}-${gloss.type ?: ""}" }
                    },
                    readingRestrictionString = entry.readings.joinToString(";") { it.restriction.joinToString("|") }
                )
            }
    }

    /*
        Use case prepends and appends '%' for sqlite LIKE query.
        For our fake repository, using a Regex instead should serve well.

        %query: any string ending with 'query'. In regex it would be query$
        query%: any string beginning with 'query'. In regex it would be ^query
     */
    private fun convertQueryToRegex(text: String): Regex {
        return Regex(pattern = when (text.indexOf("%")) {
            0 -> text.replace("%", "") + "$"
            else -> "^" + text.replace("%", "")
        })
    }

    override suspend fun getEntry(id: Long): Entry {
        return Entries.firstOrNull { it.id == id } ?: throw Exception()
    }

    override suspend fun totalCount(): Long {
        return Entries.size.toLong()
    }

    companion object {
        private val Entries = listOf(
            Entry(
                id = 2356220,
                kanjis = listOf(
                    Kanji(value = "行方向奇偶検査", info = emptyList(), priority = emptyList())
                ),
                readings = listOf(
                    JReading(
                        value = "ぎょうほうこうきぐうけんさ",
                        info = emptyList(),
                        priority = emptyList(),
                        isNotTrueReading = false,
                        restriction = emptyList()
                    )
                ),
                senses = listOf(
                    Sense(
                        glosses = listOf(Gloss(value = "longitudinal parity check", type = null)),
                        particleOfSpeeches = listOf(Info("n")),
                        fields = listOf(Info("comp")),
                        dialects = emptyList(),
                        antonyms = emptyList(),
                        example = emptyList()
                    )
                )
            ),
            Entry(
                id = 2356230,
                kanjis = listOf(
                    Kanji(value = "行列演算", info = emptyList(), priority = emptyList())
                ),
                readings = listOf(
                    JReading(
                        value = "ぎょうれつえんざん",
                        info = emptyList(),
                        priority = emptyList(),
                        isNotTrueReading = false,
                        restriction = emptyList()
                    )
                ),
                senses = listOf(
                    Sense(
                        glosses = listOf(Gloss(value = "matrix operation", type = null)),
                        particleOfSpeeches = listOf(Info("n")),
                        fields = listOf(Info("comp")),
                        dialects = emptyList(),
                        antonyms = emptyList(),
                        example = emptyList()
                    )
                )
            ),
            Entry(
                id = 2356240,
                kanjis = listOf(
                    Kanji(value = "行列記法", info = emptyList(), priority = emptyList())
                ),
                readings = listOf(
                    JReading(
                        value = "ぎょうれつきほう",
                        info = emptyList(),
                        priority = emptyList(),
                        isNotTrueReading = false,
                        restriction = emptyList()
                    )
                ),
                senses = listOf(
                    Sense(
                        glosses = listOf(Gloss(value = "matrix notation", type = null)),
                        particleOfSpeeches = listOf(Info("n")),
                        fields = listOf(Info("comp")),
                        dialects = emptyList(),
                        antonyms = emptyList(),
                        example = emptyList()
                    )
                )
            ),
            Entry(
                id = 2356250,
                kanjis = listOf(
                    Kanji(value = "行列代数", info = emptyList(), priority = emptyList())
                ),
                readings = listOf(
                    JReading(
                        value = "ぎょうれつだいすう",
                        info = emptyList(),
                        priority = emptyList(),
                        isNotTrueReading = false,
                        restriction = emptyList()
                    )
                ),
                senses = listOf(
                    Sense(
                        glosses = listOf(Gloss(value = "linear algebra", type = null), Gloss(value = "matrix algebra", type = null)),
                        particleOfSpeeches = listOf(Info("n")),
                        fields = listOf(Info("comp")),
                        dialects = emptyList(),
                        antonyms = emptyList(),
                        example = emptyList()
                    )
                )
            ),
            Entry(
                id = 2356260,
                kanjis = listOf(
                    Kanji(value = "行列表現", info = emptyList(), priority = emptyList())
                ),
                readings = listOf(
                    JReading(
                        value = "ぎょうれつひょうげん",
                        info = emptyList(),
                        priority = emptyList(),
                        isNotTrueReading = false,
                        restriction = emptyList()
                    )
                ),
                senses = listOf(
                    Sense(
                        glosses = listOf(Gloss(value = "matrix representation", type = null)),
                        particleOfSpeeches = listOf(Info("n")),
                        fields = listOf(Info("comp")),
                        dialects = emptyList(),
                        antonyms = emptyList(),
                        example = emptyList()
                    )
                )
            ),
            Entry(
                id = 2356270,
                kanjis = listOf(
                    Kanji(value = "行列要素", info = emptyList(), priority = emptyList())
                ),
                readings = listOf(
                    JReading(
                        value = "ぎょうれつようそ",
                        info = emptyList(),
                        priority = emptyList(),
                        isNotTrueReading = false,
                        restriction = emptyList()
                    )
                ),
                senses = listOf(
                    Sense(
                        glosses = listOf(Gloss(value = "matrix element", type = null)),
                        particleOfSpeeches = listOf(Info("n")),
                        fields = listOf(Info("comp")),
                        dialects = emptyList(),
                        antonyms = emptyList(),
                        example = emptyList()
                    )
                )
            ),
            Entry(
                id = 2356280,
                kanjis = listOf(
                    Kanji(value = "行枠", info = emptyList(), priority = emptyList())
                ),
                readings = listOf(
                    JReading(
                        value = "ぎょうわく",
                        info = emptyList(),
                        priority = emptyList(),
                        isNotTrueReading = false,
                        restriction = emptyList()
                    )
                ),
                senses = listOf(
                    Sense(
                        glosses = listOf(Gloss(value = "line box", type = null)),
                        particleOfSpeeches = listOf(Info("n")),
                        fields = listOf(Info("comp")),
                        dialects = emptyList(),
                        antonyms = emptyList(),
                        example = emptyList()
                    )
                )
            ),
            Entry(
                id = 2356290,
                kanjis = listOf(
                    Kanji(value = "降順キー", info = emptyList(), priority = emptyList())
                ),
                readings = listOf(
                    JReading(
                        value = "こうじゅんキー",
                        info = emptyList(),
                        priority = emptyList(),
                        isNotTrueReading = false,
                        restriction = emptyList()
                    )
                ),
                senses = listOf(
                    Sense(
                        glosses = listOf(Gloss(value = "descending key", type = null)),
                        particleOfSpeeches = listOf(Info("n")),
                        fields = listOf(Info("comp")),
                        dialects = emptyList(),
                        antonyms = emptyList(),
                        example = emptyList()
                    )
                )
            ),
            Entry(
                id = 2356310,
                kanjis = listOf(
                    Kanji(value = "項目選択", info = emptyList(), priority = emptyList())
                ),
                readings = listOf(
                    JReading(
                        value = "こうもくせんたく",
                        info = emptyList(),
                        priority = emptyList(),
                        isNotTrueReading = false,
                        restriction = emptyList()
                    )
                ),
                senses = listOf(
                    Sense(
                        glosses = listOf(Gloss(value = "item selection", type = null)),
                        particleOfSpeeches = listOf(Info("n")),
                        fields = listOf(Info("comp")),
                        dialects = emptyList(),
                        antonyms = emptyList(),
                        example = emptyList()
                    )
                )
            ),
            Entry(
                id = 2356320,
                kanjis = listOf(
                    Kanji(value = "項目名", info = emptyList(), priority = emptyList())
                ),
                readings = listOf(
                    JReading(
                        value = "こうもくめい",
                        info = emptyList(),
                        priority = emptyList(),
                        isNotTrueReading = false,
                        restriction = emptyList()
                    )
                ),
                senses = listOf(
                    Sense(
                        glosses = listOf(Gloss(value = "item name", type = null)),
                        particleOfSpeeches = listOf(Info("n")),
                        fields = listOf(Info("comp")),
                        dialects = emptyList(),
                        antonyms = emptyList(),
                        example = emptyList()
                    )
                )
            ),
            Entry(
                id = 2356350,
                kanjis = listOf(
                    Kanji(value = "高位レベル", info = emptyList(), priority = emptyList())
                ),
                readings = listOf(
                    JReading(
                        value = "こういレベル",
                        info = emptyList(),
                        priority = emptyList(),
                        isNotTrueReading = false,
                        restriction = emptyList()
                    )
                ),
                senses = listOf(
                    Sense(
                        glosses = listOf(Gloss(value = "higher level", type = null)),
                        particleOfSpeeches = listOf(Info("n")),
                        fields = listOf(Info("comp")),
                        dialects = emptyList(),
                        antonyms = emptyList(),
                        example = emptyList()
                    )
                )
            ),
            Entry(
                id = 2356360,
                kanjis = listOf(
                    Kanji(value = "高域通過フィルタ", info = emptyList(), priority = emptyList())
                ),
                readings = listOf(
                    JReading(
                        value = "こういきつうかフィルタ",
                        info = emptyList(),
                        priority = emptyList(),
                        isNotTrueReading = false,
                        restriction = emptyList()
                    )
                ),
                senses = listOf(
                    Sense(
                        glosses = listOf(Gloss(value = "high pass filter", type = null), Gloss(value = "HPF", type = null)),
                        particleOfSpeeches = listOf(Info("n")),
                        fields = listOf(Info("comp")),
                        dialects = emptyList(),
                        antonyms = emptyList(),
                        example = emptyList()
                    )
                )
            ),
            Entry(
                id = 2356370,
                kanjis = listOf(
                    Kanji(value = "高画質", info = emptyList(), priority = emptyList())
                ),
                readings = listOf(
                    JReading(
                        value = "こうがしつ",
                        info = emptyList(),
                        priority = emptyList(),
                        isNotTrueReading = false,
                        restriction = emptyList()
                    )
                ),
                senses = listOf(
                    Sense(
                        glosses = listOf(Gloss(value = "high image quality", type = null)),
                        particleOfSpeeches = listOf(Info("n")),
                        fields = listOf(Info("comp")),
                        dialects = emptyList(),
                        antonyms = emptyList(),
                        example = emptyList()
                    )
                )
            ),
            Entry(
                id = 2356380,
                kanjis = listOf(
                    Kanji(value = "高解像度", info = emptyList(), priority = emptyList())
                ),
                readings = listOf(
                    JReading(
                        value = "こうかいぞうど",
                        info = emptyList(),
                        priority = emptyList(),
                        isNotTrueReading = false,
                        restriction = emptyList()
                    )
                ),
                senses = listOf(
                    Sense(
                        glosses = listOf(Gloss(value = "high resolution", type = null)),
                        particleOfSpeeches = listOf(Info("n")),
                        fields = listOf(Info("comp")),
                        dialects = emptyList(),
                        antonyms = emptyList(),
                        example = emptyList()
                    )
                )
            ),
            Entry(
                id = 2356390,
                kanjis = listOf(
                    Kanji(value = "高級言語", info = emptyList(), priority = emptyList())
                ),
                readings = listOf(
                    JReading(
                        value = "こうきゅうげんご",
                        info = emptyList(),
                        priority = emptyList(),
                        isNotTrueReading = false,
                        restriction = emptyList()
                    )
                ),
                senses = listOf(
                    Sense(
                        glosses = listOf(Gloss(value = "high-level language", type = null)),
                        particleOfSpeeches = listOf(Info("n")),
                        fields = listOf(Info("comp")),
                        dialects = emptyList(),
                        antonyms = emptyList(),
                        example = emptyList()
                    )
                )
            ),
        )
    }

    override suspend fun getEntriesForJlpt(level: Long): List<JmdictQueryResult> {
        return emptyList()
    }

    override suspend fun getForKanjiOrReading(query: String): JmdictQueryResult? {
        return null
    }
}