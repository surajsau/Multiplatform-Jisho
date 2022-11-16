package `in`.surajsau.jisho.android.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import `in`.surajsau.jisho.android.JishoNavHost
import `in`.surajsau.jisho.navigation.AppDestination
import `in`.surajsau.jisho.navigation.rememberJishoNavController
import `in`.surajsau.jisho.ui.theme.AppTheme

@Composable
fun JishoApp(
    startDestination: AppDestination
) {
    AppTheme {
        Surface {
            val navController = rememberJishoNavController()

            JishoNavHost(
                modifier = Modifier.fillMaxSize(),
                navController = navController.navHostController,
                startDestination = startDestination.route,
                onNavigateToDestination = navController::navigate,
                onNavigateBack = navController::navigateBack
            )
        }
    }
}
