package `in`.surajsau.jisho.android.ui

import `in`.surajsau.jisho.android.ui.details.model.DetailsModel
import `in`.surajsau.jisho.model.KanjiQuery
import android.os.Bundle
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.ListAlt
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

object Navigation {
    sealed class App(val key: String) {
        object Download : App("download")
        object Home : App("home")
        data class Details(val id: Long, val word: String) : App(Key) {
            val route: String
                get() = "details/$id/$word"

            companion object {
                const val Key = "details/{id}/{word}"

                fun fromArgs(extras: Bundle): DetailsModel {
                    val id = extras.getString("id")?.toLong() ?: 0L
                    val word = extras.getString("word")!!

                    return DetailsModel(id, word)
                }
            }
        }

        data class SentenceList(val word: String): App(Key) {
            val route: String
                get() = "sentence_list/$word"

            companion object {
                const val Key = "sentence_list/{word}"

                fun fromArgs(extras: Bundle): String {
                    return extras.getString("word")!!
                }
            }
        }

        data class SentenceDetails(val id: Long): App(Key) {
            val route: String
                get() = "sentence_details/$id"

            companion object {
                const val Key = "sentence_details/{id}"

                fun fromArgs(extras: Bundle): Long {
                    return extras.getString("id")?.toLong() ?: 0L
                }
            }
        }

        data class JlptList(val level: Int): App(Key) {

            val route: String
                get() = "jlpt_list/$level"

            companion object {
                const val Key = "jlpt_list/{level}"

                fun fromArgs(extras: Bundle): Int {
                    return extras.getString("level")!!.toInt()
                }
            }
        }

        data class KanjiList(val query: KanjiQuery) : App(Key) {
            val route: String
                get() {
                    return when (query) {
                        is KanjiQuery.All -> "kanji/all"
                        is KanjiQuery.Freq -> "kanji/freq?from=${query.from}&to=${query.to}"
                        is KanjiQuery.Grade -> "kanji/grade?grade=${query.grade}"
                        is KanjiQuery.AllSchool -> "kanji/grade-all"
                    }
                }

            companion object {
                const val Key = "kanji/{type}?from={from}&to={to}&grade={grade}"

                fun fromArgs(extras: Bundle): KanjiQuery {
                    val type = extras.getString("type")!!
                    return when (type) {
                        "freq" -> {
                            val from = extras.getInt("from")
                            val to = extras.getInt("to")

                            KanjiQuery.Freq(from.toLong(), to.toLong())
                        }
                        "grade" -> {
                            val grade= extras.getString("grade")!!.toInt()
                            KanjiQuery.Grade(grade)
                        }
                        "grade-all" -> KanjiQuery.AllSchool
                        "all" -> KanjiQuery.All

                        else -> KanjiQuery.All
                    }
                }
            }
        }
    }

    sealed class Resources(val key: String) {
        object Kana: Resources("resource_kana")
        object Jlpt: Resources("resource_jlpt")
        object Kanji: Resources("resource_kanji")
    }

    sealed class Home(
        val icon: ImageVector,
        val selectedIcon: ImageVector,
        val title: String,
        val navKey: String
    ) {
        object Search : Home(icon = Icons.Outlined.Search, selectedIcon = Icons.Filled.Search, title = "Search", navKey = "search")
        object Lists : Home(icon = Icons.Outlined.ListAlt, selectedIcon = Icons.Filled.ListAlt, title = "Lists", navKey = "list")
        object Favorite : Home(icon = Icons.Outlined.Bookmark, selectedIcon = Icons.Filled.Bookmark, title = "Saved", navKey = "favorite")
        object Settings : Home(icon = Icons.Outlined.Settings, selectedIcon = Icons.Filled.Settings, title = "Settings", navKey = "settings")
    }
}