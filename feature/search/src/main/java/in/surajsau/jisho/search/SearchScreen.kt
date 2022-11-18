package `in`.surajsau.jisho.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.search.components.ResultRow
import `in`.surajsau.jisho.search.components.SearchBar

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    screenState: SearchScreenState = rememberSearchScreenState(),
    onItemClicked: (Long, String) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    val uiState = screenState.uiState

    Column(modifier = modifier) {
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            text = screenState.searchText,
            collapsed = screenState.searchBarCollapsed,
            onTextChanged = {
                screenState.onTextChanged(it)
            }
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) {
            items(
                items = uiState.results,
                key = { it.id }
            ) { item ->
                ResultRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    result = item,
                    onItemClicked = {
                        keyboardController?.hide()
                        onItemClicked(item.id, item.value)
                    }
                )
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}
