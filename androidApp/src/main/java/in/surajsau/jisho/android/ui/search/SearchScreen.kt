package `in`.surajsau.jisho.android.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.android.base.dispatch
import `in`.surajsau.jisho.android.ui.search.components.SearchBar
import `in`.surajsau.jisho.android.ui.search.components.SearchResultRow
import `in`.surajsau.jisho.viewmodel.SearchViewModel
import org.koin.androidx.compose.get

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = get(),
    onItemClicked: (Long, String) -> Unit,
) {
    val (state, intent, _) = dispatch(viewModel = viewModel)

    var searchText by remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(modifier = modifier) {
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = searchText,
            onTextChanged = {
                searchText = it
                intent(SearchViewModel.Intent.SearchTextChanged(it))
            }
        )

        when (state) {
            is SearchViewModel.State.Results -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                ) {
                    items(state.value) {
                        SearchResultRow(
                            modifier = Modifier.fillMaxWidth()
                                .padding(vertical = 8.dp),
                            result = it,
                            onItemClicked = {
                                keyboardController?.hide()
                                onItemClicked(it.id, it.value)
                            }
                        )
                    }
                }
            }
            is SearchViewModel.State.EmptyResult -> {}
            else -> {}
        }
    }
}
