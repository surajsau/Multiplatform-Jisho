package `in`.surajsau.jisho.reference.jlpt.navigation

import android.os.Bundle
import `in`.surajsau.jisho.navigation.AppDestination

object JlptResourceNavigation : AppDestination {
    override val route: String = "jlpt-resource"
}

data class JlptListNavigation(val level: Int) : AppDestination {
    override val route: String
        get() = "jlpt_list/$level"

    companion object {
        const val Route = "jlpt_list/{level}"

        fun fromArgs(extras: Bundle): Int {
            return extras.getString("level")!!.toInt()
        }
    }
}