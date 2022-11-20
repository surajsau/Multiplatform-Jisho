package `in`.surajsau.jisho.reference.jlpt.navigation

import android.os.Bundle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import `in`.surajsau.jisho.reference.jlpt.JlptListScreen
import `in`.surajsau.jisho.reference.jlpt.JlptResourceScreen

fun NavGraphBuilder.jlptNavGraph(
    navigateToJlptLevel: (Int) -> Unit,
    navigateTodetails: (Long, String) -> Unit,
) {
    composable(route = JlptNavGraph.mainRoute()) {
        JlptResourceScreen(onItemClicked = navigateToJlptLevel)
    }

    composable(
        route = JlptNavGraph.jlptListRoute("{level}"),
        arguments = listOf(
            navArgument("level") { type = NavType.IntType }
        )
    ) {
        val args = it.arguments ?: return@composable
        JlptListScreen(
            level = args.getInt("level"),
            onItemClicked = navigateTodetails
        )
    }
}

object JlptNavGraph {
    fun mainRoute(): String = "jlpt-resource"

    fun jlptListRoute(level: String): String {
        return "jlpt_list/$level"
    }
}