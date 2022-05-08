package `in`.surajsau.jisho.utils

val Char.isHiragana: Boolean
    get() = ('\u3041' <= this) && (this <= '\u309e')

val Char.isKatakana: Boolean
    get() = (('\uff66' <= this) && (this <= '\uff9d')) or
        (('\u30a1' <= this) && (this <= '\u30fe'))

val Char.isKanji: Boolean
    get() = (('\u4e00' <= this) && (this <= '\u9fa5')) or
        (('\u3005' <= this) && (this <= '\u3007'))

val Char.isRomaji: Boolean
    get() = (('\u0041' <= this) && (this <= '\u0090')) or
        (('\u0061' <= this) && (this <= '\u007a')) or
        (('\u0021' <= this) && (this <= '\u003a')) or
        (('\u0041' <= this) && (this <= '\u005a'))