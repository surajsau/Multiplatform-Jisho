package `in`.surajsau.jisho.search

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import `in`.surajsau.jisho.viewmodel.SearchUiState
import `in`.surajsau.jisho.viewmodel.SearchViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun rememberSearchScreenState(
    viewModel: SearchViewModel = koinViewModel()
): SearchScreenState {
    val keyboardController = LocalSoftwareKeyboardController.current
    return remember {
        SearchScreenState(viewModel, keyboardController)
    }
}

@Stable
@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalComposeUiApi::class)
class SearchScreenState constructor(
    private val viewModel: SearchViewModel,
    private val keyboardController: SoftwareKeyboardController?
) {
    var searchText by mutableStateOf("")

    private var isSearchTextFocused by mutableStateOf(true)

    val uiState: SearchUiState
        @Composable get() = viewModel.state.collectAsStateWithLifecycle().value

    val searchBarCollapsed: Boolean
        @Composable get() = isSearchTextFocused || uiState.results.isNotEmpty()

    fun onTextChanged(text: String) {
        searchText = text
        viewModel.onSearchTextChanged(text)
    }

    fun onFocusDismissed() {
        keyboardController?.hide()
    }

    fun onSearchResultScrolled() {
        keyboardController?.hide()
    }

    fun onFocusChanged(hasFocus: Boolean) {
        Log.e("SearchScreen", "onFocusChanged $hasFocus")
        if (!hasFocus) {
            isSearchTextFocused = false
        }
    }
}