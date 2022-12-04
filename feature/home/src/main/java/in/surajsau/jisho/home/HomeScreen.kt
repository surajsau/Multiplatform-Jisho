package `in`.surajsau.jisho.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import `in`.surajsau.jisho.tagged.components.TagsTopAppBar
import `in`.surajsau.jisho.tagged.navigation.tagsNavGraph
import `in`.surajsau.jisho.home.component.BottomNavBar
import `in`.surajsau.jisho.home.model.HomeDestination
import `in`.surajsau.jisho.home.model.HomeState
import `in`.surajsau.jisho.home.model.rememberHomeState
import `in`.surajsau.jisho.reference.components.ReferenceTopAppBar
import `in`.surajsau.jisho.reference.navigation.referenceNavGraph
import `in`.surajsau.jisho.search.navigation.searchNavGraph
import `in`.surajsau.jisho.settings.components.SettingseTopAppBar
import `in`.surajsau.jisho.settings.navigation.settingsNavGraph
import `in`.surajsau.jisho.ui.theme.components.JishoScaffold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeState = rememberHomeState(),
    onSearchItemClicked: (Long, String) -> Unit,
    onKanjiClicked: () -> Unit,
    onKanaClicked: () -> Unit,
    onJlptClicked: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    JishoScaffold(
        modifier = modifier,
        topBar = {
            when (uiState.currentDestination) {
                HomeDestination.Search -> {}
                HomeDestination.Favorites -> TagsTopAppBar(
                    scrollBehavior = scrollBehavior
                )
                HomeDestination.Reference -> ReferenceTopAppBar(
                    scrollBehavior = scrollBehavior
                )
                HomeDestination.Settings -> SettingseTopAppBar(
                    scrollBehavior = scrollBehavior
                )
            }
        },
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

            tagsNavGraph()

            referenceNavGraph(
                navigateToJlptResources = onJlptClicked,
                navigateToKanaResources = onKanaClicked,
                navigateToKanjiResources = onKanjiClicked
            )

            settingsNavGraph()
        }
    }
}