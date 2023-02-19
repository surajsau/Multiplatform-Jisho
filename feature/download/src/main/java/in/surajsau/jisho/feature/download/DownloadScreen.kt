package `in`.surajsau.jisho.feature.download

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.feature.download.model.DownloadScreenState
import `in`.surajsau.jisho.feature.download.model.rememberDownloadScreenState
import `in`.surajsau.jisho.ui.theme.JishoTheme
import `in`.surajsau.jisho.viewmodel.DownloadUiState

@Composable
fun DownloadScreen(
    modifier: Modifier = Modifier,
    state: DownloadScreenState = rememberDownloadScreenState(),
    onDownloadComplete: () -> Unit
) {
    val uiState = state.uiState
    LaunchedEffect(uiState.downloadFileExists) {
        onDownloadComplete()
    }

    DownloadScreenImpl(
        modifier = modifier,
        uiState = uiState
    )
}

@Composable
private fun DownloadScreenImpl(
    modifier: Modifier = Modifier,
    uiState: DownloadUiState
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier.fillMaxWidth(0.5f),
                text = uiState.statusMessage,
                textAlign = TextAlign.Center
            )
        }
    }
}

private class DownloadUiStateProvider: PreviewParameterProvider<DownloadUiState> {

    override val values: Sequence<DownloadUiState>
        get() = sequenceOf(
            DownloadUiState(downloadFileExists = false, statusMessage = "Downloading file..."),
            DownloadUiState(downloadFileExists = false, statusMessage = "Extracting file...")
        )
}

@Preview(showBackground = true)
@Composable
fun PreviewDownloadScreen(
    @PreviewParameter(DownloadUiStateProvider::class) uiState: DownloadUiState
) {
    JishoTheme {
        DownloadScreenImpl(
            modifier = Modifier.fillMaxSize(),
            uiState = uiState
        )
    }
}