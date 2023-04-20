package `in`.surajsau.jisho.reference.jlpt.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.composable
import `in`.surajsau.jisho.reference.jlpt.JlptListScreen
import `in`.surajsau.jisho.reference.jlpt.JlptResourceScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.jlptNavGraph(
    navigateToJlptLevel: (Int) -> Unit,
    navigateTodetails: (String, String) -> Unit,
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