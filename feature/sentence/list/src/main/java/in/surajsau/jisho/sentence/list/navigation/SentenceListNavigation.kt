package `in`.surajsau.jisho.sentence.list.navigation

import android.os.Bundle
import `in`.surajsau.jisho.navigation.AppDestination

data class SentenceListNavigation(val word: String) : AppDestination {
    override val route: String
        get() = "sentence_list/$word"

    companion object {
        private const val KeyWord = "word"
        val Route = SentenceListNavigation("{$KeyWord}").route

        fun fromArgs(extras: Bundle): String {
            return extras.getString(KeyWord)!!
        }
    }
}