package `in`.surajsau.jisho.tagged.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import `in`.surajsau.jisho.tagged.TagsScreen

fun NavGraphBuilder.tagsNavGraph() {
    composable(route = TagsNavGraph.tagsRoute()) {
        TagsScreen()
    }
}

object TagsNavGraph {
    fun tagsRoute(): String = "tags"
}