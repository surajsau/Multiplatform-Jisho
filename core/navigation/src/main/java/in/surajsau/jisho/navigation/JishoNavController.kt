package `in`.surajsau.jisho.navigation

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
fun rememberJishoNavController(): JishoNavController {
    val navHostController = rememberNavController()
    return remember { JishoNavController(navHostController) }
}

@Stable
class JishoNavController(
    val navHostController: NavHostController
) {
    var currentDestination: AppDestination? by mutableStateOf(null)

    val showBottomBar: Boolean
        @Composable get() = currentDestination is HomeDestination

    fun navigate(destination: AppDestination) {
        val startDestination = navHostController.graph.findStartDestination()

        when (destination) {
            is HomeDestination -> {
                navHostController.navigate(destination.route) {
                    popUpTo(startDestination.id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }

            else -> navHostController.navigate(destination.route)
        }
    }

    fun navigateBack() {
        navHostController.popBackStack()
    }
}