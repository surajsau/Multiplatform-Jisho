package `in`.surajsau.jisho.navigation

import androidx.annotation.DrawableRes

interface AppDestination {
    val route: String
}

data class HomeDestination(
    @DrawableRes val icon: Int,
    @DrawableRes val selectedIcon: Int,
    val title: String,
    override val route: String
): AppDestination

object NavigationItem {
    sealed class App(val key: String) {

    }

}
