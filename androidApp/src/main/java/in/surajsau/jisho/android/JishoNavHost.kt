package `in`.surajsau.jisho.android

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import `in`.surajsau.jisho.android.ui.favorite.FavoriteScreen
import `in`.surajsau.jisho.details.DetailsScreen
import `in`.surajsau.jisho.details.navigation.DetailsNavigation
import `in`.surajsau.jisho.download.DownloadScreen
import `in`.surajsau.jisho.download.navigation.DownloadNavigation
import `in`.surajsau.jisho.favorites.navigation.FavoriteDestination
import `in`.surajsau.jisho.model.KanjiQuery
import `in`.surajsau.jisho.navigation.AppDestination
import `in`.surajsau.jisho.reference.ReferenceScreen
import `in`.surajsau.jisho.reference.jlpt.JlptResourceScreen
import `in`.surajsau.jisho.reference.jlpt.list.JlptListScreen
import `in`.surajsau.jisho.reference.jlpt.navigation.JlptListNavigation
import `in`.surajsau.jisho.reference.kanji.KanjiResourceScreen
import `in`.surajsau.jisho.reference.kanji.list.KanjiListScreen
import `in`.surajsau.jisho.reference.kanji.navigation.KanjiListDestination
import `in`.surajsau.jisho.reference.navigation.ReferenceDestination
import `in`.surajsau.jisho.search.SearchScreen
import `in`.surajsau.jisho.search.navigation.SearchDestination
import `in`.surajsau.jisho.sentence.details.SentenceDetailsScreen
import `in`.surajsau.jisho.sentence.details.navigation.SentenceDetailsNavigation
import `in`.surajsau.jisho.sentence.list.SentenceListScreen
import `in`.surajsau.jisho.sentence.list.navigation.SentenceListNavigation
import `in`.surajsau.jisho.settings.SettingsScreen
import `in`.surajsau.jisho.settings.navigation.SettingsDestination

@Composable
fun JishoNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String,
    onNavigateToDestination: (AppDestination) -> Unit,
    onNavigateBack: () -> Unit
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(DownloadNavigation.route) {
            DownloadScreen(
                modifier = Modifier.fillMaxSize(),
                onDownloadComplete = { onNavigateToDestination(SearchDestination) }
            )
        }

        composable(SearchDestination.route) {
            SearchScreen(
                onItemClicked = { id, word -> onNavigateToDestination(DetailsNavigation("$id", word)) }
            )
        }

        composable(DetailsNavigation.Route) {
            val args = it.arguments ?: return@composable

            DetailsScreen(
                modifier = Modifier.fillMaxSize(),
                model = DetailsNavigation.fromArgs(args),
                navigateToSentenceList = { word ->
                    onNavigateToDestination(SentenceListNavigation(word))
                },
                navigateToSentenceDetails = { id ->
                    onNavigateToDestination(SentenceDetailsNavigation(id))
                },
                navigateBack = onNavigateBack
            )
        }

        composable(SentenceListNavigation.Route) {
            val args = it.arguments ?: return@composable
            SentenceListScreen(
                word = SentenceListNavigation.fromArgs(args),
                navigateBack = onNavigateBack,
                navigateToDetails = { id ->
                    onNavigateToDestination(SentenceDetailsNavigation(id))
                }
            )
        }

        composable(SentenceDetailsNavigation.Key) {
            val args = it.arguments ?: return@composable
            SentenceDetailsScreen(
                id = SentenceDetailsNavigation.fromArgs(args),
                navigateBack = onNavigateBack
            )
        }

        composable(KanjiListDestination.Route) {
            val args = it.arguments ?: return@composable
            KanjiListScreen(
                query = KanjiListDestination.fromArgs(args),
                navigateBack = onNavigateBack
            )
        }

        composable(JlptListNavigation.Route) {
            val args = it.arguments ?: return@composable

            JlptListScreen(
                level = JlptListNavigation.fromArgs(args),
                navigateToDetails = { id, word ->
                    onNavigateToDestination(DetailsNavigation("$id", word))
                                    },
                navigateBack = onNavigateBack
            )
        }

        composable(KanjiListDestination.Route) {
            KanjiResourceScreen(
                navigateToGradeList = { grade ->
                    onNavigateToDestination(KanjiListDestination(query = KanjiQuery.Grade(grade)))
                },
                navigateToAllGradesList = {
                    onNavigateToDestination(KanjiListDestination(query = KanjiQuery.AllSchool))
                },
                navigateBack = onNavigateBack
            )
        }

        composable(JlptListNavigation.Route) {
            JlptResourceScreen(
                navigateToJlptLevel = { level -> onNavigateToDestination(JlptListNavigation(level = level)) },
                navigateBack = onNavigateBack
            )
        }

        composable(FavoriteDestination.route) {
            FavoriteScreen()
        }

        composable(ReferenceDestination.route) {
            ReferenceScreen(
                onItemClicked = {}
            )
        }

        composable(SettingsDestination.route) {
            SettingsScreen()
        }
    }
}