package `in`.surajsau.jisho.reference.kanji.navigation

import android.os.Bundle
import `in`.surajsau.jisho.model.KanjiQuery
import `in`.surajsau.jisho.navigation.AppDestination

object KanjiResourceDestination : AppDestination {
    override val route: String = "kanji-resource"
}

data class KanjiListDestination(val query: KanjiQuery) : AppDestination {
    override val route: String
        get() {
            return when (query) {
                is KanjiQuery.All -> "kanji/all"
                is KanjiQuery.Freq -> "kanji/freq?from=${query.from}&to=${query.to}"
                is KanjiQuery.Grade -> "kanji/grade?grade=${query.grade}"
                is KanjiQuery.AllSchool -> "kanji/grade-all"
            }
        }

    companion object {
        private const val KEY_TYPE = "type"
        private const val KEY_FROM = "from"
        private const val KEY_TO = "to"
        private const val KEY_GRADE = "grade"

        const val Route = "kanji/{$KEY_TYPE}?from={$KEY_FROM}&to={$KEY_TO}&grade={$KEY_GRADE}"

        fun fromArgs(extras: Bundle): KanjiQuery {
            val type = extras.getString(KEY_TYPE)!!
            return when (type) {
                "freq" -> {
                    val from = extras.getInt(KEY_FROM)
                    val to = extras.getInt(KEY_TO)

                    KanjiQuery.Freq(from.toLong(), to.toLong())
                }
                "grade" -> {
                    val grade = extras.getString(KEY_GRADE)!!.toInt()
                    KanjiQuery.Grade(grade)
                }
                "grade-all" -> KanjiQuery.AllSchool
                "all" -> KanjiQuery.All

                else -> KanjiQuery.All
            }
        }
    }
}