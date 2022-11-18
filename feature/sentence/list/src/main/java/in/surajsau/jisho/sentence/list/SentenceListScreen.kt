package `in`.surajsau.jisho.sentence.list

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.sentence.list.components.SentenceRow
import `in`.surajsau.jisho.utils.dispatch
import `in`.surajsau.jisho.viewmodel.SentenceListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SentenceListScreen(
    modifier: Modifier = Modifier,
    word: String,
    viewModel: SentenceListViewModel = koinViewModel(),
    navigateToDetails: (Long) -> Unit
) {
    val (state, intent, _) = dispatch(viewModel)

    LaunchedEffect(Unit) {
        Log.e("SentenceList", "init $word $viewModel")
        intent(SentenceListViewModel.Intent.InitWith(word))
    }

    Column(modifier = modifier) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(
                items = state.sentences,
                key = { it.id }
            ) { sentence ->
                SentenceRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    model = sentence,
                    onClick = { navigateToDetails(sentence.id) }
                )
            }
        }
    }
}
