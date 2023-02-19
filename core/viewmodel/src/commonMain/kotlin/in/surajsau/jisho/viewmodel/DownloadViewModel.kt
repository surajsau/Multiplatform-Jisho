package `in`.surajsau.jisho.viewmodel

import `in`.surajsau.jisho.download.DownloadManager
import `in`.surajsau.jisho.download.FileStatus
import `in`.surajsau.jisho.viewmodel.expected.BaseViewModel
import `in`.surajsau.jisho.viewmodel.expected.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

public class DownloadViewModel : BaseViewModel<DownloadUiState>(), KoinComponent {

    private val downloadManager: DownloadManager = get()

    private val _downloadFileExists = MutableStateFlow(false)
    private val _statusMessage = MutableStateFlow("")

    override val state: StateFlow<DownloadUiState>
        get() = combine(_downloadFileExists, _statusMessage) { values ->
            val downloadFileExists = values[0] as Boolean
            val statusMessage = values[1] as String

            return@combine DownloadUiState(downloadFileExists, statusMessage)
        }
            .stateIn(scope, SharingStarted.WhileSubscribed(), DownloadUiState())

    public fun doInit() {
        if (downloadManager.checkIfDatabaseExists()) {
            _downloadFileExists.value = true
            return
        }

        scope.launch {
            _statusMessage.value = "Downloading file..."

            val downloadFileStatus = downloadManager.downloadFile()

            if (downloadFileStatus is FileStatus.Error) {
                _downloadFileExists.value = false
                _statusMessage.value = downloadFileStatus.exception.message ?: "Some error occured while downloading"
                return@launch
            }

            _statusMessage.value = "Extracting file..."
            val extractFileStatus = downloadManager.extractFile()

            if (extractFileStatus is FileStatus.Error) {
                _downloadFileExists.value = false
                _statusMessage.value = extractFileStatus.exception.message ?: "Some error occured while extracting"
                return@launch
            }

            _statusMessage.value = "Done"

            // to show animation
            delay(1_000)

            _downloadFileExists.value = true
        }
    }

}

public data class DownloadUiState(
    val downloadFileExists: Boolean = false,
    val statusMessage: String = ""
): UiState
