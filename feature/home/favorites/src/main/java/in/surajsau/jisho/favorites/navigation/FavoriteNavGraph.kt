package `in`.surajsau.jisho.favorites.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import `in`.surajsau.jisho.android.ui.favorite.FavoriteScreen

fun NavGraphBuilder.favoriteNavGraph() {
    composable(route = FavoriteNavGraph.favoriteRoute()) {
        FavoriteScreen()
    }
}

object FavoriteNavGraph {
    fun favoriteRoute(): String = "favorite"
}