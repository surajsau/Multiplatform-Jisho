package `in`.surajsau.jisho.home

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import `in`.surajsau.jisho.favorites.navigation.favoriteNavGraph
import `in`.surajsau.jisho.home.component.BottomNavBar
import `in`.surajsau.jisho.home.model.HomeDestination
import `in`.surajsau.jisho.home.model.HomeState
import `in`.surajsau.jisho.home.model.rememberHomeState
import `in`.surajsau.jisho.reference.navigation.referenceNavGraph
import `in`.surajsau.jisho.search.navigation.searchNavGraph
import `in`.surajsau.jisho.settings.navigation.settingsNavGraph
import `in`.surajsau.jisho.ui.theme.components.JishoScaffold

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeState = rememberHomeState(),
    onSearchItemClicked: (Long, String) -> Unit,
    onKanjiClicked: () -> Unit,
    onKanaClicked: () -> Unit,
    onJlptClicked: () -> Unit,
) {
    JishoScaffold(
        modifier = modifier,
        topBar = {},
        bottomBar = {
            BottomNavBar(
                currentDestination = uiState.currentDestination,
                onNavItemTapped = uiState::navigate
            )
        }
    ) { padding ->
        NavHost(
            modifier = Modifier.padding(padding),
            navController = uiState.navController,
            startDestination = HomeDestination.Search.route
        ) {
            searchNavGraph(
                onSearchItemClicked = onSearchItemClicked
            )

            favoriteNavGraph()

            referenceNavGraph(
                navigateToJlptResources = onJlptClicked,
                navigateToKanaResources = onKanaClicked,
                navigateToKanjiResources = onKanjiClicked
            )

            settingsNavGraph()
        }
    }
}