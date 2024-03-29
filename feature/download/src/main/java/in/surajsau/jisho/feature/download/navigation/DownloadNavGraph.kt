package `in`.surajsau.jisho.feature.download.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import `in`.surajsau.jisho.feature.download.DownloadScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.downlaodNavGraph(
     onDownloadComplete: () -> Unit
) {
    composable(route = DownloadNavGraph.downloadRoute()) {
        DownloadScreen(
            onDownloadComplete = onDownloadComplete
        )
    }
}

object DownloadNavGraph {
    fun downloadRoute(): String = "download"
}