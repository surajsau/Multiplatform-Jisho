package `in`.surajsau.jisho.tagged.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import `in`.surajsau.jisho.viewmodel.TagsUiState
import `in`.surajsau.jisho.viewmodel.TagsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun remmeberTagsScreenModel(
    viewModel: TagsViewModel = koinViewModel()
): TagsScreenModel {
    return remember { TagsScreenModel(viewModel = viewModel) }
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Stable
class TagsScreenModel (
    private val viewModel: TagsViewModel
) {
    var showAddTagDialog by mutableStateOf(false)

    val uiState: TagsUiState
        @Composable get() = viewModel.state.collectAsStateWithLifecycle().value

    fun onAddTagCardClicked() {
        showAddTagDialog = true
    }

    fun onAddTagDialogDismissed() {
        showAddTagDialog = false
    }
}