package `in`.surajsau.jisho.details.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import `in`.surajsau.jisho.details.DetailsScreen
import `in`.surajsau.jisho.details.model.DetailsModel
import `in`.surajsau.jisho.details.rememberDetailsScreenState

fun NavGraphBuilder.detailsNavGraph(
    onShowMoreSentenceClick: (String) -> Unit,
    onSentenceItemClick: (Long) -> Unit,
    onBackClick: () -> Unit
) {
    composable(
        route = DetailsNavGraph.detailsRoute("{id}", "{word}"),
        arguments = listOf(
            navArgument("id") { type = NavType.LongType },
            navArgument("word") { type = NavType.StringType }
        ),
        enterTransition = { slideInHorizontally { it } },
        exitTransition = { slideOutHorizontally { it } }
    ) {
        val args = it.arguments ?: return@composable
        val id = args.getLong("id")
        val word = args.getString("word", "")
        DetailsScreen(
            state = rememberDetailsScreenState(
                model = DetailsModel(id, word)
            ),
            onSentenceItemClick = onSentenceItemClick,
            onShowMoreSentenceClick = onShowMoreSentenceClick,
            onBackClick = onBackClick
        )
    }
}

object DetailsNavGraph {
    fun detailsRoute(id: String, word: String): String {
        return "details/$id/$word"
    }

    fun isDetailsRoute(route: String) = route.contains("details")
}