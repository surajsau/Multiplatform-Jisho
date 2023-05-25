package `in`.surajsau.jisho.reference.kanji

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import `in`.surajsau.jisho.model.KanjiQuery
import `in`.surajsau.jisho.model.KanjiResult
import `in`.surajsau.jisho.reference.kanji.components.KanjiGridItem
import `in`.surajsau.jisho.viewmodel.KanjiListViewModel
import org.koin.compose.rememberKoinInject

@Composable
fun KanjiListScreen(
    modifier: Modifier = Modifier,
    query: KanjiQuery,
    viewModel: KanjiListViewModel = rememberKoinInject(),
    onGridItemTap: (KanjiResult) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.initWith(query)
    }

    
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(minSize = 56.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(all = 16.dp),
        userScrollEnabled = true,
    ) {
        items(state.items) { item ->
            KanjiGridItem(
                item = item,
                onClick = { onGridItemTap(item) }
            )
        }
    }
}
