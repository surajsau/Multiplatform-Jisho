package `in`.surajsau.jisho.reference.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import `in`.surajsau.jisho.reference.ReferenceScreen

fun NavGraphBuilder.referenceNavGraph(
    navigateToKanaResources: () -> Unit,
    navigateToKanjiResources: () -> Unit,
    navigateToJlptResources: () -> Unit
) {
    composable(route = ReferenceNavGraph.referenceRoute()) {
        ReferenceScreen(
            onKanaTapped = navigateToKanaResources,
            onKanjiTapped = navigateToKanjiResources,
            onJlptTapped = navigateToJlptResources
        )
    }
}

object ReferenceNavGraph {
    fun referenceRoute(): String = "reference"
}