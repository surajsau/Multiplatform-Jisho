package `in`.surajsau.jisho.details

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import `in`.surajsau.jisho.details.model.DetailsModel
import `in`.surajsau.jisho.viewmodel.DetailsUiState
import `in`.surajsau.jisho.viewmodel.DetailsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun rememberDetailsScreenState(
    model: DetailsModel,
    viewModel: DetailsViewModel = koinViewModel(),
    scrollState: ScrollState = rememberScrollState()
): DetailsScreenState {
    return remember(model) {
        DetailsScreenState(scrollState, model, viewModel)
    }
}

class DetailsScreenState(
    val scrollState: ScrollState,
    val model: DetailsModel,
    private val viewModel: DetailsViewModel
) {
    init {
        viewModel.initWith(id = model.id, word = model.word)
    }

    val uiState: DetailsUiState
        @Composable get() = viewModel.state.collectAsStateWithLifecycle().value

    fun onLikeClicked() {
        viewModel.onEditTagClicked()
    }
}