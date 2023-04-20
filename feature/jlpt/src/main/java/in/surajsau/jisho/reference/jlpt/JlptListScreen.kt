package `in`.surajsau.jisho.reference.jlpt

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import `in`.surajsau.jisho.reference.jlpt.components.ResultRow
import `in`.surajsau.jisho.viewmodel.JlptListViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun JlptListScreen(
    modifier: Modifier = Modifier,
    level: Int,
    viewModel: JlptListViewModel = getViewModel(),
    onItemClicked: (id: String, word: String) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.initWith(level)
    }

    if (state.isLoading) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
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
                        onItemClicked(item.id, item.value)
                    }
                )
            }
        }
    }
}
