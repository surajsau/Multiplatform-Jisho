package `in`.surajsau.jisho.tagged.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.tagsNavGraph() {
    composable(route = TagsNavGraph.tagsRoute()) {
//        TagsScreen(screenModel = rememberTa)
    }
}

object TagsNavGraph {
    fun tagsRoute(): String = "tags"
}