package `in`.surajsau.jisho.reference.jlpt.navigation

import android.os.Bundle
import `in`.surajsau.jisho.navigation.AppDestination

object JlptResourceDestination : AppDestination {
    override val route: String = "jlpt-resource"
}

data class JlptListDestination(val level: String) : AppDestination {
    override val route: String
        get() = "jlpt_list/$level"

    companion object {
        private const val KeyLevel = "level"
        val Route = JlptListDestination("{$KeyLevel}").route

        fun fromArgs(extras: Bundle): Int {
            return extras.getString("level")!!.toInt()
        }
    }
}