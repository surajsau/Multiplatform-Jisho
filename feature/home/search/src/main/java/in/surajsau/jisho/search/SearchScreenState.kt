package `in`.surajsau.jisho.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import `in`.surajsau.jisho.viewmodel.SearchState
import `in`.surajsau.jisho.viewmodel.SearchViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun rememberSearchScreenState(
    viewModel: SearchViewModel = koinViewModel()
): SearchScreenState {
    return remember { SearchScreenState(viewModel) }
}

@Stable
@OptIn(ExperimentalLifecycleComposeApi::class)
class SearchScreenState(private val viewModel: SearchViewModel) {
    var searchText by mutableStateOf("")

    val uiState: SearchState
        @Composable get() = viewModel.state.collectAsStateWithLifecycle().value

    val searchBarCollapsed: Boolean
        @Composable get() = uiState.results.isNotEmpty()

    fun onTextChanged(text: String) {
        searchText = text
        viewModel.onSearchTextChanged(text)
    }
}