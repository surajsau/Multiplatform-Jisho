package `in`.surajsau.jisho.android.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import `in`.surajsau.jisho.android.JishoNavHost
import `in`.surajsau.jisho.app.AppViewModel
import `in`.surajsau.jisho.navigation.rememberJishoNavController
import `in`.surajsau.jisho.ui.theme.JishoTheme
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun JishoApp(
    viewModel: AppViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    JishoTheme(
        themePreference = state.themePreference
    ) {
        Surface {
            val navController = rememberJishoNavController()

            JishoScaffold(
                onNavItemTapped = navController::navigate,
                currentDestination = navController.currentDestination
            ) { padding ->
                JishoNavHost(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    navController = navController.navHostController,
                    startDestination = state.startDestination.route,
                    onNavigateToDestination = navController::navigate
                )
            }
        }
    }
}
