package `in`.surajsau.jisho.details

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import `in`.surajsau.jisho.details.model.DetailsModel
import `in`.surajsau.jisho.viewmodel.DetailsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun rememberDetailsScreenState(
    model: DetailsModel,
    viewModel: DetailsViewModel = koinViewModel(),
    scrollState: ScrollState = rememberScrollState(),
    navigateToSentenceList: (String) -> Unit,
    navigateToSentenceDetails: (Long) -> Unit,
): DetailsScreenState {
    return remember {
        DetailsScreenState(scrollState, model, viewModel, navigateToSentenceList, navigateToSentenceDetails)
    }
}

@OptIn(ExperimentalLifecycleComposeApi::class)
class DetailsScreenState(
    val scrollState: ScrollState,
    private val model: DetailsModel,
    private val viewModel: DetailsViewModel,
    private val navigateToSentenceList: (String) -> Unit,
    private val navigateToSentenceDetails: (Long) -> Unit,
) {
    init {
        viewModel.onIntent(DetailsViewModel.Intent.InitWith(id = model.id, word = model.word))
    }

    val uiState: DetailsViewModel.State
        @Composable get() = viewModel.state.collectAsStateWithLifecycle().value

    fun onShowMoreClicked() {
        navigateToSentenceList(model.word)
    }

    fun onSentenceItemClicked(id: Long) {
        navigateToSentenceDetails(id)
    }
}