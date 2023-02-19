package `in`.surajsau.jisho.reference.kanji.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.composable
import `in`.surajsau.jisho.model.KanjiQuery
import `in`.surajsau.jisho.model.KanjiResult
import `in`.surajsau.jisho.reference.kanji.KanjiListScreen
import `in`.surajsau.jisho.reference.kanji.KanjiResourceScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.kanjiNavGraph(
    onGradeItemClicked: (Int) -> Unit,
    onAllGradesClicked: () -> Unit,
    onKanjiGridItemClicked: (KanjiResult) -> Unit
) {
    composable(route = KanjiNavGraph.mainRoute()) {
        KanjiResourceScreen(
            onGradeItemClicked = onGradeItemClicked,
            onAllGradesClicked = onAllGradesClicked
        )
    }

    composable(route = KanjiNavGraph.kanjiAllGradesRoute()) {
        KanjiListScreen(
            query = KanjiQuery.AllSchool,
            onGridItemTap = onKanjiGridItemClicked
        )
    }

    composable(route = KanjiNavGraph.kanjiAllRoute()) {
        KanjiListScreen(
            query = KanjiQuery.All,
            onGridItemTap = onKanjiGridItemClicked
        )
    }

    composable(
        route = KanjiNavGraph.kanjiFrequencyRoute("{from}", "{to}"),
        arguments = listOf(
            navArgument("from") { type = NavType.LongType },
            navArgument("to") { type = NavType.LongType }
        )
    ) {
        val args = it.arguments ?: return@composable
        val from = args.getLong("from")
        val to = args.getLong("to")

        KanjiListScreen(
            query = KanjiQuery.Freq(from, to),
            onGridItemTap = onKanjiGridItemClicked
        )
    }

    composable(
        route = KanjiNavGraph.kanjiGradeRoute("{grade}"),
        arguments = listOf(
            navArgument("grade") { type = NavType.IntType }
        )
    ) {
        val args = it.arguments ?: return@composable
        val grade = args.getInt("grade")

        KanjiListScreen(
            query = KanjiQuery.Grade(grade),
            onGridItemTap = onKanjiGridItemClicked
        )
    }
}

object KanjiNavGraph {
    fun kanjiAllRoute(): String {
        return "kanji/all"
    }

    fun kanjiFrequencyRoute(from: String, to: String): String {
        return "kanji/freq?from=$from&to=$to"
    }

    fun kanjiGradeRoute(grade: String): String {
        return "kanji/grade?grade=$grade"
    }

    fun kanjiAllGradesRoute(): String {
        return "kanji/grade-all"
    }

    internal fun kanjiListPlaceholderRoute(): String {
        return "kanji/{type}?from={from}&to={to}&grade={grade}"
    }

    fun mainRoute(): String {
        return "kanji-resource"
    }
}