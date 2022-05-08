package `in`.surajsau.jisho.android.ui

import `in`.surajsau.jisho.android.ui.details.DetailsScreen
import `in`.surajsau.jisho.android.ui.download.DownloadScreen
import `in`.surajsau.jisho.android.ui.home.HomeScreen
import `in`.surajsau.jisho.android.ui.reference.kanji.KanjiListScreen
import `in`.surajsau.jisho.android.ui.sentence.details.SentenceDetailsScreen
import `in`.surajsau.jisho.android.ui.sentence.list.SentenceListScreen
import `in`.surajsau.jisho.android.ui.theme.DarkColors
import `in`.surajsau.jisho.android.ui.theme.LightColors
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun JishoApp() {
    MaterialTheme(
        colors = if (isSystemInDarkTheme()) DarkColors else LightColors
    ) {
        Surface {
            val navController = rememberNavController()

            NavHost(
                modifier = Modifier.fillMaxSize(),
                navController = navController,
                startDestination = Navigation.App.Download.key,
            ) {
                composable(Navigation.App.Download.key) {
                    DownloadScreen(
                        modifier = Modifier.fillMaxSize(),
                        onDownloadComplete = { navController.navigate(Navigation.App.Home.key) }
                    )
                }

                composable(Navigation.App.Home.key) {
                    HomeScreen(
                        modifier = Modifier.fillMaxSize(),
                        navigateToKanjiList = {},
                        navigateToDetails = { id, word ->
                            navController.navigate(route = Navigation.App.Details(id, word).route)
                        }
                    )
                }

                composable(Navigation.App.Details.Key) {
                    val args = it.arguments ?: return@composable

                    DetailsScreen(
                        modifier = Modifier.fillMaxSize(),
                        model = Navigation.App.Details.fromArgs(args),
                        navigateToSentenceList = { word ->
                            navController.navigate(route = Navigation.App.SentenceList(word).route)
                        },
                        navigateToSentenceDetails = { id ->
                            navController.navigate(route = Navigation.App.SentenceDetails(id).route)
                        },
                        navigateBack = { navController.navigateUp() }
                    )
                }

                composable(Navigation.App.SentenceList.Key) {
                    val args = it.arguments ?: return@composable
                    SentenceListScreen(
                        modifier = Modifier.fillMaxSize(),
                        word = Navigation.App.SentenceList.fromArgs(args),
                        navigateBack = { navController.navigateUp() },
                        navigateToDetails = { id ->
                            navController.navigate(route = Navigation.App.SentenceDetails(id).route)
                        }
                    )
                }

                composable(Navigation.App.SentenceDetails.Key) {
                    val args = it.arguments ?: return@composable
                    SentenceDetailsScreen(
                        modifier = Modifier.fillMaxSize(),
                        id = Navigation.App.SentenceDetails.fromArgs(args),
                        navigateBack = { navController.navigateUp() }
                    )
                }

                composable(Navigation.App.KanjiList.Key) {
                    val args = it.arguments ?: return@composable
                    KanjiListScreen(
                        modifier = Modifier.fillMaxSize(),
                        query = Navigation.App.KanjiList.fromArgs(args),
                        navigateBack = { navController.navigateUp() }
                    )
                }
            }
        }
    }
}