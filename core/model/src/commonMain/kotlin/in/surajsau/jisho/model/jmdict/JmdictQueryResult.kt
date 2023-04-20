package `in`.surajsau.jisho.model.jmdict

public data class JmdictQueryResult(
    val id: Long,

    /**
     * Would require split(;) to obtain the values.
     *
     * e.g., 楽しい;愉しい;娯しい
     */
    val kanjiString: String,

    /**
     * Would require split(;) to obtain the values.
     *
     * e.g., たのしい
     */
    val readingString: String,

    /**
     * Would require split(;) to obtain the restrictions.
     * Choose the appropriate restriction based on the kanjiString or readingString.
     *
     * e.g., あっという間に|あっと言う間に;あっと言う間に|あっとゆう間に;アッという間に|アッと言う間に;アッと言う間に|アッとゆう間に
     */
    val readingRestrictionString: String,

    /**
     * Would require split(|) to obtain each gloss.
     * Then split(-) to obtain the value and type.
     *
     * e.g., enjoyable-None|fun-None|pleasant-None|happy-None|delightful-None
     */
    val glossString: String,
)
