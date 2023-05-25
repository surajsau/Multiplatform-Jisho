package `in`.surajsau.jisho.android.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import `in`.surajsau.jisho.app.AppViewModel
import `in`.surajsau.jisho.details.navigation.DetailsNavGraph
import `in`.surajsau.jisho.feature.download.navigation.DownloadNavGraph
import `in`.surajsau.jisho.home.navigation.HomeNavGraph
import `in`.surajsau.jisho.model.ThemePreference
import `in`.surajsau.jisho.reference.jlpt.navigation.JlptNavGraph
import `in`.surajsau.jisho.reference.kanji.navigation.KanjiNavGraph
import `in`.surajsau.jisho.sentence.navigation.SentenceNavGraph
import org.koin.androidx.compose.koinViewModel

@Composable
fun rememberAppState(
    startDestination: String,
    navController: NavHostController = rememberNavController(),
    viewModel: AppViewModel = koinViewModel()
): AppState {
    return remember {
        AppState(
            navController = navController,
            startDestination = startDestination,
            viewModel = viewModel
        )
    }
}

@Stable
class AppState (
    val navController: NavHostController,
    val startDestination: String,
    val viewModel: AppViewModel
) {
    private val _viewModelState: AppViewModel.State
        @Composable get() = viewModel.state.collectAsStateWithLifecycle().value

    val currentDestination: String
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination?.route ?: startDestination

    val showBottomBar: Boolean
        @Composable get() = `in`.surajsau.jisho.home.model.HomeDestination.isRouteAHomeDestination(currentDestination)

    val themePreference: ThemePreference
        @Composable get() = _viewModelState.themePreference

    fun navigateToDetails(id: String, word: String) {
        if (id.startsWith("w")) {
            val detailId = id.substring(1)
            navController.navigate(DetailsNavGraph.detailsRoute(id = detailId, word = word))
        }
    }

    fun navigateToHome() {
        navController.navigate(HomeNavGraph.homeRoute())
    }

    fun navigateToDownload() {
        navController.navigate(DownloadNavGraph.downloadRoute())
    }

    fun navigateToSentenceDetails(id: Long) {
        navController.navigate(SentenceNavGraph.sentenceDetailsRoute("$id"))
    }

    fun navigateToSentenceList(word: String) {
        navController.navigate(SentenceNavGraph.sentenceListRoute(word))
    }

    fun navigateToJlpt() {
        navController.navigate(JlptNavGraph.mainRoute())
    }

    fun navigateToKanji() {
        navController.navigate(KanjiNavGraph.mainRoute())
    }

    fun navigateToKanjiAll() {
        navController.navigate(KanjiNavGraph.kanjiAllRoute())
    }

    fun navigateToKanjiGrade(grade: Int) {
        navController.navigate(KanjiNavGraph.kanjiGradeRoute("$grade"))
    }

    fun navigateToKanjiFrequency(from: Long, to: Long) {
        navController.navigate(KanjiNavGraph.kanjiFrequencyRoute("$from", "$to"))
    }

    fun navigateToKanjiAllGrades() {
        navController.navigate(KanjiNavGraph.kanjiAllGradesRoute())
    }

    fun navigateToJlptList(level: Int) {
        navController.navigate(JlptNavGraph.jlptListRoute("$level"))
    }

    fun navigateBack() {
        navController.popBackStack()
    }
}