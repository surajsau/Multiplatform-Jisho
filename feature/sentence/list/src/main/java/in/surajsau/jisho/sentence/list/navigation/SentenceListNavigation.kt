package `in`.surajsau.jisho.sentence.list.navigation

import android.os.Bundle
import `in`.surajsau.jisho.navigation.AppDestination
import `in`.surajsau.jisho.navigation.NavigationItem

data class SentenceListNavigation(val word: String) : AppDestination {
    override val route: String
        get() = "sentence_list/$word"

    companion object {
        private const val KEY_WORD = "word"
        val Route = SentenceListNavigation(KEY_WORD).route

        fun fromArgs(extras: Bundle): String {
            return extras.getString(KEY_WORD)!!
        }
    }
}