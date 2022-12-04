package `in`.surajsau.jisho.home.model

import androidx.annotation.DrawableRes
import `in`.surajsau.jisho.tagged.navigation.FavoriteNavGraph
import `in`.surajsau.jisho.ui.R
import `in`.surajsau.jisho.reference.navigation.ReferenceNavGraph
import `in`.surajsau.jisho.search.navigation.SearchNavGraph
import `in`.surajsau.jisho.settings.navigation.SettingsNavGraph

enum class HomeDestination(
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val icon: Int,
    val label: String,
    val route: String
) {
    Search(
        label = "Search",
        icon = R.drawable.ic_search,
        selectedIcon = R.drawable.ic_search,
        route = SearchNavGraph.searchRoute()
    ),
    Favorites(
        label = "Favorites",
        icon = R.drawable.ic_like,
        selectedIcon = R.drawable.ic_like_filled,
        route = FavoriteNavGraph.favoriteRoute()
    ),
    Reference(
        label = "Lists",
        icon = R.drawable.ic_list,
        selectedIcon = R.drawable.ic_list,
        route = ReferenceNavGraph.referenceRoute()
    ),
    Settings(
        label = "Settings",
        icon = R.drawable.ic_settings,
        selectedIcon = R.drawable.ic_settings,
        route = SettingsNavGraph.settingsRoute()
    );

    companion object {
        fun isRouteAHomeDestination(route: String): Boolean {
            return values().any { it.route == route }
        }
    }
}