package `in`.surajsau.jisho.search

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.surajsau.jisho.search.components.SearchBar
import `in`.surajsau.jisho.search.components.ResultRow
import `in`.surajsau.jisho.utils.dispatch
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

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(modifier = modifier) {
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            text = when(state) {
                is SearchViewModel.State.Results -> state.searchText
                is SearchViewModel.State.EmptyResult -> state.searchText
                else -> ""
            },
            collapsed = state is SearchViewModel.State.Results,
            onTextChanged = {
                intent(SearchViewModel.Intent.SearchTextChanged(it))
            }
        )

        when (state) {
            is SearchViewModel.State.Results -> {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                ) {
                    item { Spacer(modifier = Modifier.height(16.dp)) }

                    items(
                        items = state.value,
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
            is SearchViewModel.State.EmptyResult -> {}
            else -> {}
        }
    }
}
