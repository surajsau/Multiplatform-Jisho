package `in`.surajsau.jisho.settings.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import `in`.surajsau.jisho.settings.SettingsScreen

fun NavGraphBuilder.settingsNavGraph() {
    composable(route = SettingsNavGraph.settingsRoute()) {
        SettingsScreen()
    }
}

object SettingsNavGraph {
    fun settingsRoute(): String = "settings"
}