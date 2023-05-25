package `in`.surajsau.jisho.reference.kanji.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import `in`.surajsau.jisho.model.KanjiQuery
import `in`.surajsau.jisho.model.KanjiResult
import `in`.surajsau.jisho.reference.kanji.KanjiListScreen
import `in`.surajsau.jisho.reference.kanji.KanjiResourceScreen

fun NavGraphBuilder.kanjiNavGraph(
    onGradeItemClicked: (Int) -> Unit,
    onAllGradesClicked: () -> Unit,
    onKanjiGridItemClicked: (KanjiResult) -> Unit,
    onBackClicked: () -> Unit,
) {
    composable(route = KanjiNavGraph.mainRoute()) {
        KanjiResourceScreen(
            onGradeItemClicked = onGradeItemClicked,
            onAllGradesClicked = onAllGradesClicked,
            onBackClicked = onBackClicked
        )
    }

    composable(route = KanjiNavGraph.kanjiAllGradesRoute()) {
        KanjiListScreen(
            query = KanjiQuery.AllSchool,
            onGridItemTap = onKanjiGridItemClicked,
            onBackClicked = onBackClicked
        )
    }

    composable(route = KanjiNavGraph.kanjiAllRoute()) {
        KanjiListScreen(
            query = KanjiQuery.All,
            onGridItemTap = onKanjiGridItemClicked,
            onBackClicked = onBackClicked
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
            onGridItemTap = onKanjiGridItemClicked,
            onBackClicked = onBackClicked
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
            onGridItemTap = onKanjiGridItemClicked,
            onBackClicked = onBackClicked
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
        return "kanji/grade?grade=${grade}"
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