package `in`.surajsau.jisho.android.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import `in`.surajsau.jisho.favorites.components.FavoriteTopAppBar
import `in`.surajsau.jisho.favorites.navigation.FavoriteDestination
import `in`.surajsau.jisho.navigation.AppDestination
import `in`.surajsau.jisho.navigation.HomeDestination
import `in`.surajsau.jisho.reference.components.ReferenceTopAppBar
import `in`.surajsau.jisho.reference.navigation.ReferenceDestination
import `in`.surajsau.jisho.search.navigation.SearchDestination
import `in`.surajsau.jisho.settings.components.SettingseTopAppBar
import `in`.surajsau.jisho.settings.navigation.SettingsDestination

private val HomeDestinations = listOf(
    SearchDestination,
    FavoriteDestination,
    ReferenceDestination,
    SettingsDestination
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JishoScaffold(
    modifier: Modifier = Modifier,
    onNavItemTapped: (HomeDestination) -> Unit,
    currentDestination: AppDestination?,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            when (currentDestination) {
                FavoriteDestination -> FavoriteTopAppBar()
                ReferenceDestination -> ReferenceTopAppBar()
                SettingsDestination -> SettingseTopAppBar()
            }
        },
        bottomBar = {
            BottomNavBar(
                destinations = HomeDestinations,
                onNavItemTapped = onNavItemTapped,
                currentDestination = currentDestination,
            )
        },
        content = content
    )
}