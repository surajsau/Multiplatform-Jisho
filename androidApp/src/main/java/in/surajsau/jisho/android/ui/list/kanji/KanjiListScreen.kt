package `in`.surajsau.jisho.android.ui.list.kanji

import `in`.surajsau.jisho.android.base.dispatch
import `in`.surajsau.jisho.android.ui.components.AppToolbar
import `in`.surajsau.jisho.android.ui.list.kanji.components.KanjiGridItem
import `in`.surajsau.jisho.model.KanjiQuery
import `in`.surajsau.jisho.viewmodel.KanjiListViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.get

@OptIn(ExperimentalMaterialApi::class)
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
            navigateUpIcon = {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null
                )
            },
            title = { Text(text = "Kanji List") },
            onNavigateUp = {  }
        )

        LazyVerticalGrid(
            modifier = Modifier.fillMaxWidth(),
            columns = GridCells.Adaptive(minSize = 56.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalArrangement = Arrangement.Center,
            contentPadding = PaddingValues(all = 8.dp),
            userScrollEnabled = true,
        ) {
            items(state.items) { item ->
                KanjiGridItem(item = item)
            }
        }
    }
}