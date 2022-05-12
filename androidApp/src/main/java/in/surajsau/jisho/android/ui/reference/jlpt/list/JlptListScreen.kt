package `in`.surajsau.jisho.android.ui.reference.jlpt.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.android.base.dispatch
import `in`.surajsau.jisho.android.ui.components.AppToolbar
import `in`.surajsau.jisho.android.ui.search.components.SearchResultRow
import `in`.surajsau.jisho.viewmodel.JlptListViewModel
import org.koin.androidx.compose.get

@Composable
fun JlptListScreen(
    modifier: Modifier = Modifier,
    level: Int,
    viewModel: JlptListViewModel = get(),
    navigateToDetails: (id: Long, word: String) -> Unit,
    navigateBack: () -> Unit = {}
) {
    val (state, intent, _) = dispatch(viewModel)

    LaunchedEffect(Unit) {
        intent(JlptListViewModel.Intent.InitWith(level))
    }

    Column(modifier = modifier) {
        AppToolbar(
            modifier = Modifier.fillMaxWidth(),
            title = state.title,
            navigateUpIcon = Icons.Default.ArrowBack,
            onNavigateUp = navigateBack
        )

        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp),
            ) {
                items(state.items) { item ->
                    SearchResultRow(
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
}
