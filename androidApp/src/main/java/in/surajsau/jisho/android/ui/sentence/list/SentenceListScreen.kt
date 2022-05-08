package `in`.surajsau.jisho.android.ui.sentence.list

import `in`.surajsau.jisho.android.base.dispatch
import `in`.surajsau.jisho.android.ui.components.AppToolbar
import `in`.surajsau.jisho.android.ui.sentence.list.components.SentenceRow
import `in`.surajsau.jisho.android.ui.theme.AppTheme
import `in`.surajsau.jisho.viewmodel.SentenceListViewModel
import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.get

@Composable
fun SentenceListScreen (
    modifier: Modifier = Modifier,
    word: String,
    viewModel: SentenceListViewModel = get(),
    navigateToDetails: (Long) -> Unit,
    navigateBack: () -> Unit = {},
) {

    val (state, intent, _) = dispatch(viewModel)

    LaunchedEffect(Unit) {
        intent(SentenceListViewModel.Intent.InitWith(word))
    }

    Column(modifier = modifier) {
        AppToolbar(
            modifier = Modifier.fillMaxWidth(),
            navigateUpIcon = Icons.Default.ArrowBack,
            title = "Sentences for $word",
            onNavigateUp = navigateBack
        )

        LazyColumn(modifier = Modifier.fillMaxWidth()) {

            items(state.sentences) { sentence ->
                SentenceRow(
                    modifier = Modifier.fillMaxWidth()
                        .clickable { navigateToDetails(sentence.id) }
                        .padding(bottom = 8.dp),
                    model = sentence
                )
            }
        }
    }
}