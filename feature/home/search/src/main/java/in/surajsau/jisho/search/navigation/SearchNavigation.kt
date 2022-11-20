package `in`.surajsau.jisho.search.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import `in`.surajsau.jisho.search.SearchScreen

fun NavGraphBuilder.searchNavGraph(
    onSearchItemClicked: (Long, String) -> Unit
) {
    composable(route = "search") {
        SearchScreen(onItemClicked = onSearchItemClicked)
    }
}

object SearchNavGraph {
    fun searchRoute(): String = "search"
}