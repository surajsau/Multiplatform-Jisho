package `in`.surajsau.jisho.android.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import `in`.surajsau.jisho.details.components.DetailsTopAppBar
import `in`.surajsau.jisho.details.navigation.DetailsNavigation
import `in`.surajsau.jisho.favorites.components.FavoriteTopAppBar
import `in`.surajsau.jisho.favorites.navigation.FavoriteDestination
import `in`.surajsau.jisho.navigation.AppDestination
import `in`.surajsau.jisho.navigation.HomeDestination
import `in`.surajsau.jisho.reference.components.ReferenceTopAppBar
import `in`.surajsau.jisho.reference.jlpt.components.JlptListTopAppBar
import `in`.surajsau.jisho.reference.jlpt.components.JlptTopAppBar
import `in`.surajsau.jisho.reference.jlpt.navigation.JlptListDestination
import `in`.surajsau.jisho.reference.jlpt.navigation.JlptResourceDestination
import `in`.surajsau.jisho.reference.kanji.components.KanjiTopAppBar
import `in`.surajsau.jisho.reference.kanji.navigation.KanjiResourceDestination
import `in`.surajsau.jisho.reference.navigation.ReferenceDestination
import `in`.surajsau.jisho.search.navigation.SearchDestination
import `in`.surajsau.jisho.sentence.details.components.SentenceDetailsTopBar
import `in`.surajsau.jisho.sentence.details.navigation.SentenceDetailsNavigation
import `in`.surajsau.jisho.sentence.list.components.SentenceListTopBar
import `in`.surajsau.jisho.sentence.list.navigation.SentenceListNavigation
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
    showBottomBar: Boolean,
    onNavItemTapped: (HomeDestination) -> Unit,
    navigateBack: () -> Unit,
    currentDestination: AppDestination?,
    content: @Composable (PaddingValues) -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = modifier
            .nestedScroll(connection = scrollBehavior.nestedScrollConnection),
        topBar = {
            when(currentDestination) {
                FavoriteDestination -> FavoriteTopAppBar(scrollBehavior = scrollBehavior)
                ReferenceDestination -> ReferenceTopAppBar(scrollBehavior = scrollBehavior)
                SettingsDestination -> SettingseTopAppBar(scrollBehavior = scrollBehavior)
                is DetailsNavigation -> DetailsTopAppBar(
                    scrollBehavior = scrollBehavior,
                    onBackClicked = navigateBack
                )
                is SentenceListNavigation -> SentenceListTopBar(
                    scrollBehavior = scrollBehavior,
                    onBackClicked = navigateBack
                )
                is SentenceDetailsNavigation -> SentenceDetailsTopBar(
                    scrollBehavior = scrollBehavior,
                    onBackClicked = navigateBack
                )
                KanjiResourceDestination -> KanjiTopAppBar(
                    scrollBehavior = scrollBehavior,
                    onBackClicked = navigateBack
                )
                JlptResourceDestination -> JlptTopAppBar(
                    scrollBehavior = scrollBehavior,
                    onBackClicked = navigateBack
                )
                is JlptListDestination -> JlptListTopAppBar(
                    scrollBehavior = scrollBehavior,
                    onBackClicked = navigateBack
                )
                else -> {}
            }
        },
        bottomBar = {
            if (showBottomBar ) {
                BottomNavBar(
                    destinations = HomeDestinations,
                    onNavItemTapped = onNavItemTapped,
                    currentDestination = currentDestination,
                )
            }
        },
        content = content
    )
}