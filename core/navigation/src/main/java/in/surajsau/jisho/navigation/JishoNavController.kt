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
fun rememberJishoNavController(
    startDestination: AppDestination
): JishoNavController {
    val navHostController = rememberNavController()
    return remember {
        JishoNavController(
            startDestination = startDestination,
            navHostController = navHostController
        )
    }
}

@Stable
class JishoNavController(
    private val startDestination: AppDestination,
    val navHostController: NavHostController
) {
    var currentDestination: AppDestination by mutableStateOf(startDestination)

    val showBottomBar: Boolean
        get() = currentDestination is HomeDestination

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

        currentDestination = destination
    }

    fun navigateBack() {
        navHostController.popBackStack()
    }
}