package `in`.surajsau.jisho.android.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import `in`.surajsau.jisho.android.ui.NavigationItem
import `in`.surajsau.jisho.android.ui.favorite.FavoriteScreen
import `in`.surajsau.jisho.android.ui.home.components.AppBottomNavBar
import `in`.surajsau.jisho.android.ui.reference.ReferenceScreen
import `in`.surajsau.jisho.android.ui.search.SearchScreen
import `in`.surajsau.jisho.android.ui.settings.SettingsScreen

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetails: (Long, String) -> Unit,
    navigateToResources: (NavigationItem.Resources) -> Unit,
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = modifier,
        bottomBar = {
            AppBottomNavBar(
                modifier = Modifier.fillMaxWidth(),
                onItemSelected = {
                    navController.navigate(it.navKey) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { padding ->
        NavHost(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            startDestination = NavigationItem.Home.Search.navKey,
            navController = navController
        ) {
            composable(NavigationItem.Home.Search.navKey) {
                SearchScreen(
                    modifier = Modifier.fillMaxSize(),
                    onItemClicked = navigateToDetails,
                )
            }

            composable(NavigationItem.Home.Favorite.navKey) {
                FavoriteScreen(modifier = Modifier.fillMaxSize())
            }

            composable(NavigationItem.Home.Lists.navKey) {
                ReferenceScreen(
                    modifier = Modifier.fillMaxSize(),
                    onItemClicked = navigateToResources
                )
            }

            composable(NavigationItem.Home.Settings.navKey) {
                SettingsScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}
