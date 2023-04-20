package `in`.surajsau.jisho.home.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import `in`.surajsau.jisho.home.HomeScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.homeNavGraph(
    onSearchItemClicked: (String, String) -> Unit,
    onKanjiClicked: () -> Unit,
    onKanaClicked: () -> Unit,
    onJlptClicked: () -> Unit,
) {
    composable(
        route = HomeNavGraph.homeRoute()
    ) {
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