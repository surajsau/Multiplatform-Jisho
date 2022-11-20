package `in`.surajsau.jisho.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import `in`.surajsau.jisho.favorites.navigation.favoriteNavGraph
import `in`.surajsau.jisho.home.HomeScreen
import `in`.surajsau.jisho.home.model.HomeDestination
import `in`.surajsau.jisho.reference.navigation.referenceNavGraph
import `in`.surajsau.jisho.search.navigation.searchNavGraph
import `in`.surajsau.jisho.settings.navigation.settingsNavGraph

fun NavGraphBuilder.homeNavGraph(
    onSearchItemClicked: (Long, String) -> Unit,
    onKanjiClicked: () -> Unit,
    onKanaClicked: () -> Unit,
    onJlptClicked: () -> Unit,
) {
    composable(route = HomeNavGraph.homeRoute()) {
        HomeScreen(

            onSearchItemClicked = onSearchItemClicked,
            onKanjiClicked = onKanjiClicked,
            onKanaClicked = onKanaClicked,
            onJlptClicked = onJlptClicked
        )
    }
}

object HomeNavGraph {
    fun homeRoute(): String = "home"
}