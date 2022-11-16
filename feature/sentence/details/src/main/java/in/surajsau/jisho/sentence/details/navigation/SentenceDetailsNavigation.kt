package `in`.surajsau.jisho.sentence.details.navigation

import android.os.Bundle
import `in`.surajsau.jisho.navigation.AppDestination

data class SentenceDetailsNavigation(val id: Long) : AppDestination {
    override val route: String
        get() = "sentence_details/$id"

    companion object {
        const val Key = "sentence_details/{id}"

        fun fromArgs(extras: Bundle): Long {
            return extras.getString("id")?.toLong() ?: 0L
        }
    }
}