package `in`.surajsau.jisho.android.ui.reference.kanji.list

import `in`.surajsau.jisho.android.base.dispatch
import `in`.surajsau.jisho.android.ui.components.AppToolbar
import `in`.surajsau.jisho.android.ui.reference.kanji.list.components.KanjiGridItem
import `in`.surajsau.jisho.model.KanjiQuery
import `in`.surajsau.jisho.viewmodel.KanjiListViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.get

@Composable
fun KanjiListScreen(
    modifier: Modifier = Modifier,
    query: KanjiQuery,
    viewModel: KanjiListViewModel = get(),
    navigateBack: () -> Unit = {}
) {

    val (state, intent, effect) = dispatch(viewModel)

    LaunchedEffect(Unit) {
        intent(KanjiListViewModel.Intent.InitWith(query))

        effect
            .onEach {
                when (it) {
                    is KanjiListViewModel.Effect.NavigateToDetails -> {}
                }
            }
            .collect()
    }

    Column(modifier = modifier) {
        AppToolbar(
            modifier = Modifier.fillMaxWidth(),
            navigateUpIcon = Icons.Filled.ArrowBack,
            title = "Kanji List",
            onNavigateUp = navigateBack
        )

        LazyVerticalGrid(
            modifier = Modifier.fillMaxWidth(),
            columns = GridCells.Adaptive(minSize = 56.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(all = 16.dp),
            userScrollEnabled = true,
        ) {
            items(state.items) { item ->
                KanjiGridItem(item = item)
            }
        }
    }
}