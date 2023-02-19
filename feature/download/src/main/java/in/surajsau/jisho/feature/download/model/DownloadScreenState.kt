package `in`.surajsau.jisho.feature.download.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import `in`.surajsau.jisho.viewmodel.DownloadUiState
import `in`.surajsau.jisho.viewmodel.DownloadViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun rememberDownloadScreenState(
    viewModel: DownloadViewModel = koinViewModel()
): DownloadScreenState {
    return remember(viewModel) {
        DownloadScreenState(viewModel = viewModel)
    }
}

class DownloadScreenState(
    private val viewModel: DownloadViewModel
) {
    val uiState: DownloadUiState
        @Composable get() = viewModel.state.collectAsStateWithLifecycle().value
}