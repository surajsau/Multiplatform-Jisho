package `in`.surajsau.jisho.data

import `in`.surajsau.jisho.data.db.Jisho
import `in`.surajsau.jisho.data.expected.TestDispatcherProvider
import `in`.surajsau.jisho.data.expected.testDbConnection
import `in`.surajsau.jisho.data.real.JmdictRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@OptIn(ExperimentalCoroutinesApi::class)
class JmdictRepositoryTest {

    private lateinit var db: Jisho

    @BeforeTest
    fun setup() {
        db = Jisho.invoke(testDbConnection())

        db.jishoQueries.insertEntry(
            1, "在外資産", "", "", "ざいがいしさん", "F",
            "", "", "", "", "n", "", "", "foreign assets-None", "", "", null
        )

        db.jishoQueries.insertEntry(
            2, "たくあん漬け;沢庵漬;沢庵漬け;たくあん漬", ";;;", ";;;", "たくあんづけ", "F",
            "", "", "", "", "n", "", "", "pickled daikon (radish)-None", "", "", null
        )

        db.jishoQueries.insertEntry(
            3, "使い勝手;使いがって;使いかって", ";;", ";;", "つかいがって;つかいかって", "F;F",
            "使い勝手|使いがって;使い勝手|使いかって", ";", ";", "", "n", "", "", "ease of use-None|user-friendliness-None|usability-None|utility-None|convenience-None", "", "", null
        )

        db.jishoQueries.insertEntry(
            4, "論う", "", "", "あげつらう", "F",
            "", "", "", ";", "v5u|vt;v5u|vt", ";", ";", "to discuss-None;to find fault with-None|to criticize-None|to criticise-None", ";あげつらう", ";彼はいろいろ他人の欠点をあげつらうのにうんざりしてしまった。そこで彼らの言うことは何でも、たとえどんなにくだらなくてもそのまま認めることにしている。/He got tired of being the devil's advocate and now agrees with every idea they suggest, no matter how dumb.", null
        )

        db.jishoQueries.insertEntry(
            5, "会う;逢う;遭う;遇う", ";;;rK", "ichi1|news2|nf26;ichi1;ichi1|news2|nf34;", "あう", "F",
            "", "", "ichi1|news2|nf26", ";", "v5u|vi;v5u|vi", ";", ";", "to meet-None|to encounter-None|to see-None;to have an accident-None|to have a bad experience-None", "あった;", "前に彼にあったのを覚えている。/I remember seeing him before.;", null
        )

        db.jishoQueries.insertEntry(
            6, "目にあう;目に遭う;めに遭う;目に会う;めに会う", ";;;iK;iK", ";;;;", "めにあう", "F",
            "", "", "ichi1|news2|nf26", "", "exp|v5u", "", "", "to go through-None|to suffer-None|to experience (something unpleasant)-None", "目にあった|目にあわず", "我々はつらい目にあった。/We had a rough time.|もう少し知恵があったら、彼は面倒な目にあわずにすんだのに。/With a little more wisdom, he would not have got in trouble.", null
        )
    }

    @Test
    fun `test totalCount`() = runTest {
        val repository = JmdictRepositoryImpl(
            db = db,
            dispatcherProvider = TestDispatcherProvider(testScheduler)
        )
        val result = repository.totalCount()
        assertEquals(result, 6)
    }

    @Test
    fun `test getEntry`() = runTest {
        val repository = JmdictRepositoryImpl(
            db = db,
            dispatcherProvider = TestDispatcherProvider(testScheduler)
        )
        val result = repository.getEntry(2)
        assertEquals(result.kanjis.size, 4)
        assertEquals(result.readings.size, 1)
        assertEquals(result.senses.size, 1)
        assertEquals(result.senses[0].glosses.size, 1)
        assertEquals(result.kanjis[0].value , "たくあん漬け")
        assertEquals(result.kanjis[1].value, "沢庵漬")
        assertEquals(result.kanjis[2].value, "沢庵漬け")
        assertEquals(result.kanjis[3].value, "たくあん漬")
        assertEquals(result.readings[0].value, "たくあんづけ")
        assertFalse(result.readings[0].isNotTrueReading)
        assertEquals(result.senses[0].glosses[0].value, "pickled daikon (radish)")
    }

    @Test
    fun `test searchWithKanji`() = runTest {
        val repository = JmdictRepositoryImpl(
            db = db,
            dispatcherProvider = TestDispatcherProvider(testScheduler)
        )

        val result = repository.searchForKanji("%会う%")
        assertEquals(result.size, 2)
        assertEquals(result[0].kanjiString, "会う;逢う;遭う;遇う")
        assertEquals(result[0].readingString, "あう")
        assertEquals(result[0].glossString, "to meet-None|to encounter-None|to see-None;to have an accident-None|to have a bad experience-None")
        assertEquals(result[1].kanjiString, "目にあう;目に遭う;めに遭う;目に会う;めに会う")
        assertEquals(result[1].readingString, "めにあう")
        assertEquals(result[1].glossString, "to go through-None|to suffer-None|to experience (something unpleasant)-None")
    }

    @Test
    fun `test getForKanjiOrReading`() = runTest {
        val repository = JmdictRepositoryImpl(
            db = db,
            dispatcherProvider = TestDispatcherProvider(testScheduler)
        )

        val kanjiMatchResult = repository.getForKanjiOrReading("%会う%")
        assertNotNull(kanjiMatchResult)
        assertEquals(kanjiMatchResult.kanjiString, "会う;逢う;遭う;遇う")
        assertEquals(kanjiMatchResult.readingString, "あう")

        val readingMatchResult = repository.getForKanjiOrReading("%ざいがいしさん%")
        assertNotNull(readingMatchResult)
        assertEquals(readingMatchResult.kanjiString, "在外資産")

        val emptyResult = repository.getForKanjiOrReading("%ご%")
        assertNull(emptyResult)
    }
}
