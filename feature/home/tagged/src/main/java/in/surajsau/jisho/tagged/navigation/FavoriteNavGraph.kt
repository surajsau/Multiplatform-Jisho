package `in`.surajsau.jisho.tagged.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import `in`.surajsau.jisho.tagged.FavoriteScreen

fun NavGraphBuilder.favoriteNavGraph() {
    composable(route = FavoriteNavGraph.favoriteRoute()) {
        FavoriteScreen()
    }
}

object FavoriteNavGraph {
    fun favoriteRoute(): String = "favorite"
}