package `in`.surajsau.jisho.sentence.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import `in`.surajsau.jisho.sentence.SentenceDetailsScreen
import `in`.surajsau.jisho.sentence.SentenceListScreen

fun NavGraphBuilder.sentenceNavGraph(
    onSentenceListItemClicked: (Long) -> Unit,
    onBackClick: () -> Unit
) {

    composable(
        route = SentenceNavGraph.sentenceListRoute("{word}"),
        arguments = listOf(
            navArgument("word") { type = NavType.StringType }
        )
    ) {
        val args = it.arguments ?: return@composable
        SentenceListScreen(
            word = args.getString("word", ""),
            onItemClick = onSentenceListItemClicked,
            onBackClick = onBackClick
        )
    }

    composable(
        route = SentenceNavGraph.sentenceDetailsRoute("{id}"),
        arguments = listOf(
            navArgument("level") { type = NavType.LongType }
        )
    ) {
        val args = it.arguments ?: return@composable
        SentenceDetailsScreen(
            id = args.getLong("id"),
            onBackClick = onBackClick
        )
    }
}

object SentenceNavGraph {
    fun sentenceDetailsRoute(id: String): String {
        return "sentence_details/$id"
    }

    fun sentenceListRoute(word: String): String {
        return "sentence_list/$word"
    }
}