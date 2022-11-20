package `in`.surajsau.jisho.home.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
internal fun rememberHomeState(): HomeState {
    val navController = rememberNavController()
    return remember {
        HomeState(navController = navController)
    }
}

@Stable
class HomeState(
    val navController: NavHostController,
) {
    var currentDestination by mutableStateOf(HomeDestination.Search)
        private set

    fun navigate(destination: HomeDestination) {
        currentDestination = destination

        val startDestination = navController.graph.findStartDestination()

        navController.navigate(destination.route) {
            popUpTo(startDestination.id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}