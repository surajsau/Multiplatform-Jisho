package `in`.surajsau.jisho.feature.download.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import `in`.surajsau.jisho.download.DownloadManager
import `in`.surajsau.jisho.download.FileStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.koin.androidx.compose.get

@Composable
fun rememberDownloadState(): DownloadState {
    val downloadManager = get<DownloadManager>()

    return remember {
        DownloadState(
            downloadManager = downloadManager
        )
    }
}

class DownloadState(
    private val downloadManager: DownloadManager
) {
    var showProgress by mutableStateOf(false)
        private set

    suspend fun download() = withContext(Dispatchers.IO) {
        showProgress = true

        if (downloadManager.downloadFile() is FileStatus.Error) {
            return@withContext
        }

        if ((downloadManager.extractFile() is FileStatus.Error)) {
            return@withContext
        }

        showProgress = false

        delay(1000)
    }
}