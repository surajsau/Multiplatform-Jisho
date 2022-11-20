package `in`.surajsau.jisho.feature.download

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.feature.download.model.DownloadState
import `in`.surajsau.jisho.feature.download.model.rememberDownloadState

@Composable
fun DownloadScreen(
    modifier: Modifier = Modifier,
    uiState: DownloadState = rememberDownloadState(),
    onDownloadComplete: () -> Unit
) {
    LaunchedEffect(Unit) {
        uiState.download()
        onDownloadComplete()
    }

    Box(modifier = modifier.background(Color.White)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (uiState.showProgress) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(16.dp))
            }

            Text(
                modifier = Modifier.fillMaxWidth(0.5f),
                text = if (uiState.showProgress) "Loading data..." else "Loaded",
                textAlign = TextAlign.Center
            )
        }
    }
}