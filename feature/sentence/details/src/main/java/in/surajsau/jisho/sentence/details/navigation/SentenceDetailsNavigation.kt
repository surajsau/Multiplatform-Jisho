package `in`.surajsau.jisho.sentence.details.navigation

import android.os.Bundle
import `in`.surajsau.jisho.navigation.AppDestination

data class SentenceDetailsNavigation(val id: String) : AppDestination {
    override val route: String
        get() = "sentence_details/$id"

    companion object {
        private const val KeyId = "id"
        val Route = SentenceDetailsNavigation(id = "{$KeyId}").route

        fun fromArgs(extras: Bundle): Long {
            return extras.getString(KeyId)?.toLong() ?: 0L
        }
    }
}