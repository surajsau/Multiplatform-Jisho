package `in`.surajsau.jisho.android.ui.download

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.android.download.Downloader
import `in`.surajsau.jisho.android.download.FileStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@Composable
fun DownloadScreen(
    modifier: Modifier = Modifier,
    onDownloadComplete: () -> Unit = {}
) {
    val context = LocalContext.current

    val downloader = remember { Downloader(context) }

    var showProgress by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        withContext(coroutineContext + Dispatchers.IO) {
            if (downloader.checkIfDatabaseExists()) {
                withContext(this@LaunchedEffect.coroutineContext) {
                    onDownloadComplete()
                }

                return@withContext
            }

            showProgress = true

            if (downloader.downloadFile("jmdict.db.gz") is FileStatus.Error) {
                return@withContext
            }

            if ((downloader.extractFile("jmdict.db.gz") is FileStatus.Error)) {
                return@withContext
            }

            showProgress = false
            delay(1000)

            withContext(this@LaunchedEffect.coroutineContext) {
                onDownloadComplete()
            }
        }
    }

    Box(modifier = modifier.background(Color.White)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (showProgress) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(16.dp))
            }

            Text(
                modifier = Modifier.fillMaxWidth(0.5f),
                text = if (showProgress) "Loading data..." else "Loaded",
                textAlign = TextAlign.Center
            )
        }
    }
}