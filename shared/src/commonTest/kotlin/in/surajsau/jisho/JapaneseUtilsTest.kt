package `in`.surajsau.jisho

import `in`.surajsau.jisho.utils.isHiragana
import `in`.surajsau.jisho.utils.isKanji
import `in`.surajsau.jisho.utils.isKatakana
import `in`.surajsau.jisho.utils.isRomaji
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class JapaneseUtilsTest {

    @Test
    fun `test isHiragana`() {
        assertTrue { 'か'.isHiragana }
        assertTrue { 'ん'.isHiragana }
        assertFalse { 'カ'.isHiragana }
        assertFalse { '火'.isHiragana }
    }

    @Test
    fun `test isKatakana`() {
        assertFalse { 'か'.isKatakana }
        assertTrue { 'カ'.isKatakana }
        assertTrue { 'ン'.isKatakana }
        assertFalse { '火'.isKatakana }
    }

    @Test
    fun `test isKanji`() {
        assertFalse { 'か'.isKanji }
        assertFalse { 'カ'.isKanji }
        assertTrue { '火'.isKanji }
        assertTrue { '週'.isKanji }
    }

    @Test
    fun `test isRomaji`() {
        assertTrue { 'a'.isRomaji }
        assertTrue { 'z'.isRomaji }
        assertFalse { '火'.isRomaji }
    }
}