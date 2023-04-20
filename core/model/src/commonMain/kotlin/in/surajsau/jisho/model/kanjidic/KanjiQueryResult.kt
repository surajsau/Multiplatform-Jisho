package `in`.surajsau.jisho.model.kanjidic

public data class KanjiQueryResult(
    /**
     * e.g., 8
     */
    val id: Long,

    /**
     * e.g., 姶
     */
    val value: String,

    /**
     * e.g., yan3-pinyin;ab-korean_r;압-korean_h;Áp-vietnam;オウ-ja_on;アイ-ja_on;あい-ja_kun
     */
    val readingString: String,

    /**
     * e.g., good-looking-None;quiet-None
     */
    val meaningString: String,
)