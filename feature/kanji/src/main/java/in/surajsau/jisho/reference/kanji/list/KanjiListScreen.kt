package `in`.surajsau.jisho.reference.kanji.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.utils.dispatch
import `in`.surajsau.jisho.reference.kanji.list.components.KanjiGridItem
import `in`.surajsau.jisho.model.KanjiQuery
import `in`.surajsau.jisho.model.KanjiResult
import `in`.surajsau.jisho.viewmodel.KanjiListViewModel
import org.koin.androidx.compose.get

@Composable
fun KanjiListScreen(
    modifier: Modifier = Modifier,
    query: KanjiQuery,
    viewModel: KanjiListViewModel = get(),
    onGridItemTap: (KanjiResult) -> Unit
) {
    val (state, intent, _) = dispatch(viewModel)

    LaunchedEffect(Unit) {
        intent(KanjiListViewModel.Intent.InitWith(query))
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
