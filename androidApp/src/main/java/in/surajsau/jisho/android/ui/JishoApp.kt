package `in`.surajsau.jisho.android.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import `in`.surajsau.jisho.android.ui.details.DetailsScreen
import `in`.surajsau.jisho.android.ui.download.DownloadScreen
import `in`.surajsau.jisho.android.ui.home.HomeScreen
import `in`.surajsau.jisho.android.ui.reference.jlpt.JlptResourceScreen
import `in`.surajsau.jisho.android.ui.reference.jlpt.list.JlptListScreen
import `in`.surajsau.jisho.android.ui.reference.kanji.KanjiResourceScreen
import `in`.surajsau.jisho.android.ui.reference.kanji.list.KanjiListScreen
import `in`.surajsau.jisho.android.ui.sentence.details.SentenceDetailsScreen
import `in`.surajsau.jisho.android.ui.sentence.list.SentenceListScreen
import `in`.surajsau.jisho.android.ui.theme.DarkColors
import `in`.surajsau.jisho.android.ui.theme.LightColors
import `in`.surajsau.jisho.model.KanjiQuery

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
                startDestination = NavigationItem.App.Download.key,
            ) {
                composable(NavigationItem.App.Download.key) {
                    DownloadScreen(
                        modifier = Modifier.fillMaxSize(),
                        onDownloadComplete = { navController.navigate(NavigationItem.App.Home.key) }
                    )
                }

                composable(NavigationItem.App.Home.key) {
                    HomeScreen(
                        modifier = Modifier.fillMaxSize(),
                        navigateToResources = { resources ->
                            navController.navigate(route = resources.key)
                        },
                        navigateToDetails = { id, word ->
                            navController.navigate(route = NavigationItem.App.Details(id, word).route)
                        }
                    )
                }

                composable(NavigationItem.App.Details.Key) {
                    val args = it.arguments ?: return@composable

                    DetailsScreen(
                        modifier = Modifier.fillMaxSize(),
                        model = NavigationItem.App.Details.fromArgs(args),
                        navigateToSentenceList = { word ->
                            navController.navigate(route = NavigationItem.App.SentenceList(word).route)
                        },
                        navigateToSentenceDetails = { id ->
                            navController.navigate(route = NavigationItem.App.SentenceDetails(id).route)
                        },
                        navigateBack = { navController.navigateUp() }
                    )
                }

                composable(NavigationItem.App.SentenceList.Key) {
                    val args = it.arguments ?: return@composable
                    SentenceListScreen(
                        modifier = Modifier.fillMaxSize(),
                        word = NavigationItem.App.SentenceList.fromArgs(args),
                        navigateBack = { navController.navigateUp() },
                        navigateToDetails = { id ->
                            navController.navigate(route = NavigationItem.App.SentenceDetails(id).route)
                        }
                    )
                }

                composable(NavigationItem.App.SentenceDetails.Key) {
                    val args = it.arguments ?: return@composable
                    SentenceDetailsScreen(
                        modifier = Modifier.fillMaxSize(),
                        id = NavigationItem.App.SentenceDetails.fromArgs(args),
                        navigateBack = { navController.navigateUp() }
                    )
                }

                composable(NavigationItem.App.KanjiList.Key) {
                    val args = it.arguments ?: return@composable
                    KanjiListScreen(
                        modifier = Modifier.fillMaxSize(),
                        query = NavigationItem.App.KanjiList.fromArgs(args),
                        navigateBack = { navController.navigateUp() }
                    )
                }

                composable(NavigationItem.App.JlptList.Key) {
                    val args = it.arguments ?: return@composable

                    JlptListScreen(
                        modifier = Modifier.fillMaxSize(),
                        level = NavigationItem.App.JlptList.fromArgs(args),
                        navigateToDetails = { id, word ->
                            navController.navigate(NavigationItem.App.Details(id, word).route)
                        },
                        navigateBack = { navController.navigateUp() }
                    )
                }

                composable(NavigationItem.Resources.Kanji.key) {
                    KanjiResourceScreen(
                        modifier = Modifier.fillMaxSize(),
                        navigateToGradeList = { grade ->
                            val route = NavigationItem.App.KanjiList(query = KanjiQuery.Grade(grade)).route
                            navController.navigate(route)
                        },
                        navigateToAllGradesList = {
                            val route = NavigationItem.App.KanjiList(query = KanjiQuery.AllSchool).route
                            navController.navigate(route)
                        },
                        navigateBack = { navController.navigateUp() }
                    )
                }

                composable(NavigationItem.Resources.Jlpt.key) {
                    JlptResourceScreen(
                        modifier = Modifier.fillMaxSize(),
                        navigateToJlptLevel = { level ->
                            val route = NavigationItem.App.JlptList(level = level).route
                            navController.navigate(route)
                        },
                        navigateBack = { navController.navigateUp() }
                    )
                }
            }
        }
    }
}
