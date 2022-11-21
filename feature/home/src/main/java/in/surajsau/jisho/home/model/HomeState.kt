package `in`.surajsau.jisho.home.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
internal fun rememberHomeState(): HomeState {
    val navController = rememberNavController()
    return rememberSaveable(
        saver = HomeStateSaver(navController = navController)
    ) {
        HomeState(
            navController = navController,
            startDestination = HomeDestination.Search
        )
    }
}

@Stable
class HomeState(
    val navController: NavHostController,
    startDestination: HomeDestination
) {
    var currentDestination by mutableStateOf(startDestination)
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

private class HomeStateSaver(
    private val navController: NavHostController
): Saver<HomeState, HomeDestination> {
    override fun restore(value: HomeDestination): HomeState? {
        return HomeState(
            navController = navController,
            startDestination = value
        )
    }

    override fun SaverScope.save(value: HomeState): HomeDestination {
        return value.currentDestination
    }

}