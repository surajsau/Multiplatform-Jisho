package `in`.surajsau.jisho

import `in`.surajsau.jisho.data.model.jmdict.JmdictQueryResult
import `in`.surajsau.jisho.data.model.kanjidic.KanjiQueryResult
import `in`.surajsau.jisho.mapper.mapToSearchResult
import kotlin.test.Test
import kotlin.test.assertEquals

class SearchResultMapperTest {

    @Test
    fun `test mapToSearchResult for multiple kanji - single reading with full search term`() {
        val queryResult = JmdictQueryResult(
            id = 0L,
            kanjiString = "お仕舞い;お終い;御仕舞い;御終い;お仕舞;御仕舞",
            readingString = "おしまい",
            glossString = "the end-None|closing-None|being done for-None",
            readingRestrictionString = "",
        )

        val searchResult = queryResult.mapToSearchResult("お終い")

        assertEquals(searchResult.reading, "おしまい")
        assertEquals(searchResult.value, "お終い")
        assertEquals(searchResult.meanings, "the end, closing, being done for")
    }

    @Test
    fun `test mapToSearchResult for multiple kanji - single reading with half search term`() {
        val queryResult = JmdictQueryResult(
            id = 0L,
            kanjiString = "お仕舞い;お終い;御仕舞い;御終い;お仕舞;御仕舞",
            readingString = "おしまい",
            glossString = "the end-None|closing-None|being done for-None",
            readingRestrictionString = "",
        )

        val searchResult = queryResult.mapToSearchResult("御仕")

        assertEquals(searchResult.reading, "おしまい")
        assertEquals(searchResult.value, "御仕舞い")
        assertEquals(searchResult.meanings, "the end, closing, being done for")
    }

    @Test
    fun `test mapToSearchResult for multiple kanji - multiple reading`() {
        val queryResult = JmdictQueryResult(
            id = 0L,
            kanjiString = "お歯黒;御歯黒;鉄漿",
            readingString = "おはぐろ;かね;てっしょう",
            glossString = "the end-None|closing-None|being done for-None",
            readingRestrictionString = ";鉄漿;鉄漿"
        )

        val searchResult = queryResult.mapToSearchResult("鉄漿")

        assertEquals(searchResult.reading, "かね")
        assertEquals(searchResult.value, "鉄漿")
        assertEquals(searchResult.meanings, "the end, closing, being done for")
    }

    @Test
    fun `test mapToSearchResult for multiple kanji - multiple reading with alternative search term`() {
        val queryResult = JmdictQueryResult(
            id = 0L,
            kanjiString = "御襁褓気触れ;お襁褓気触れ;オムツ気触れ",
            readingString = "おむつかぶれ;オムツかぶれ",
            glossString = "diaper rash-None|nappy rash-None",
            readingRestrictionString = "御襁褓気触れ|お襁褓気触れ;オムツ気触れ"
        )

        val searchResult = queryResult.mapToSearchResult("お襁褓気")

        assertEquals(searchResult.reading, "おむつかぶれ")
        assertEquals(searchResult.value, "お襁褓気触れ")
        assertEquals(searchResult.meanings, "diaper rash, nappy rash")
    }

    @Test
    fun `test mapToSearchResult for kanji literal`() {
        val literal = KanjiQueryResult(
            value = "楽",
            readingString = "le4-pinyin;yue4-pinyin;ag-korean_r;악-korean_h;Lạc-vietnam;Nhạc-vietnam;ガク-ja_on;ラク-ja_on;ゴウ-ja_on;たの.しい-ja_kun;たの.しむ-ja_kun;この.む-ja_kun",
            meaningString = "music-None;comfort-None;ease-None;musique-fr;agréable-fr;confort-fr;música-es;divertido-es;fácil-es;agradable-es;disfrutar-es;divertirse-es;música-pt;conforto-pt;facilidade-pt"
        )

        val searchResult = literal.mapToSearchResult()

        assertEquals(searchResult.reading, "ガク, ラク, ゴウ, たの.しい, たの.しむ, この.む")
        assertEquals(searchResult.value, "楽")
        assertEquals(searchResult.meanings, "music, comfort, ease")
    }
}