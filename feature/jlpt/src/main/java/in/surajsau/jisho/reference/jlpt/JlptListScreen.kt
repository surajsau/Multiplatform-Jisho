package `in`.surajsau.jisho.reference.jlpt

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.reference.jlpt.components.ResultRow
import `in`.surajsau.jisho.utils.dispatch
import `in`.surajsau.jisho.viewmodel.JlptListViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun JlptListScreen(
    modifier: Modifier = Modifier,
    level: Int,
    viewModel: JlptListViewModel = getViewModel(),
    navigateToDetails: (id: Long, word: String) -> Unit,
) {
    val (state, intent, _) = dispatch(viewModel)

    LaunchedEffect(Unit) {
        intent(JlptListViewModel.Intent.InitWith(level))
    }

    if (state.isLoading) {
        Box(modifier = modifier) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) {
            items(state.items) { item ->
                ResultRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    result = item,
                    onItemClicked = {
                        navigateToDetails(item.id, item.value)
                    }
                )
            }
        }
    }
}
