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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.search.components.ResultRow
import `in`.surajsau.jisho.search.components.SearchBar

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    screenState: SearchScreenState = rememberSearchScreenState(),
    onItemClicked: (Long, String) -> Unit,
) {
    val uiState = screenState.uiState

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Column(modifier = modifier) {
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .onFocusChanged {
                    screenState.onFocusChanged(it.hasFocus)
                },
            text = screenState.searchText,
            focused = screenState.searchBarFocused,
            onTextChanged = screenState::onTextChanged,
            onKeyboardAction = { focusRequester.freeFocus() }
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
                        focusRequester.freeFocus()
                        screenState.onFocusDismissed()
                        onItemClicked(item.id, item.value)
                    }
                )
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}
