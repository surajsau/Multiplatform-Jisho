package `in`.surajsau.jisho.android.ui

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import `in`.surajsau.jisho.android.model.rememberAppState
import `in`.surajsau.jisho.details.navigation.detailsNavGraph
import `in`.surajsau.jisho.feature.download.navigation.downlaodNavGraph
import `in`.surajsau.jisho.home.navigation.homeNavGraph
import `in`.surajsau.jisho.reference.jlpt.navigation.jlptNavGraph
import `in`.surajsau.jisho.reference.kanji.navigation.kanjiNavGraph
import `in`.surajsau.jisho.sentence.navigation.sentenceNavGraph
import `in`.surajsau.jisho.ui.theme.JishoTheme

@Composable
fun JishoApp(
    startDestination: String,
) {
    val appState = rememberAppState(startDestination = startDestination)

    JishoTheme(
        themePreference = appState.themePreference
    ) {
        Surface {
            NavHost(
                navController = appState.navController,
                startDestination = startDestination,
            ) {
                homeNavGraph(
                    onSearchItemClicked = appState::navigateToDetails,
                    onJlptClicked = appState::navigateToJlpt,
                    onKanaClicked = {},
                    onKanjiClicked = appState::navigateToKanji
                )

                detailsNavGraph(
                    onShowMoreSentenceClick = appState::navigateToSentenceList,
                    onSentenceItemClick = appState::navigateToSentenceDetails,
                    onBackClick = appState::navigateBack
                )

                kanjiNavGraph(
                    onAllGradesClicked = appState::navigateToKanjiAllGrades,
                    onKanjiGridItemClicked = {},
                    onGradeItemClicked = appState::navigateToKanjiGrade
                )

                jlptNavGraph(
                    navigateTodetails = appState::navigateToDetails,
                    navigateToJlptLevel = appState::navigateToJlptList
                )

                downlaodNavGraph(
                    onDownloadComplete = appState::navigateToHome
                )

                sentenceNavGraph(
                    onSentenceListItemClicked = appState::navigateToSentenceDetails,
                    onBackClick = appState::navigateBack
                )
            }
        }
    }
}
