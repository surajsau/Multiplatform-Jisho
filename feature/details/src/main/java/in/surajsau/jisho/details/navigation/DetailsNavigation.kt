package `in`.surajsau.jisho.details.navigation

import android.os.Bundle
import `in`.surajsau.jisho.details.model.DetailsModel
import `in`.surajsau.jisho.navigation.AppDestination

data class DetailsNavigation(val id: String, val word: String) : AppDestination {
    override val route: String
        get() = "details/$id/$word"

    companion object {
        private const val KEY_ID = "id"
        private const val KEY_WORD = "word"

        val Route = DetailsNavigation("{$KEY_ID}", "{$KEY_WORD}").route

        fun fromArgs(extras: Bundle): DetailsModel {
            val id = extras.getString(KEY_ID)?.toLong() ?: 0L
            val word = extras.getString(KEY_WORD)!!

            return DetailsModel(id, word)
        }
    }
}