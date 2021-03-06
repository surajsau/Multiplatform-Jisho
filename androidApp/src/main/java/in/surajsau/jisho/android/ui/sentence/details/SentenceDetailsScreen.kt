package `in`.surajsau.jisho.android.ui.sentence.details

import `in`.surajsau.jisho.android.base.dispatch
import `in`.surajsau.jisho.android.ui.components.AppToolbar
import `in`.surajsau.jisho.android.ui.sentence.details.components.NotesTextField
import `in`.surajsau.jisho.viewmodel.SentenceDetailViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.get

@Composable
fun SentenceDetailsScreen(
    modifier: Modifier = Modifier,
    id: Long,
    viewModel: SentenceDetailViewModel = get(),
    navigateBack: () -> Unit = {}
) {
    val (state, intent, _) = dispatch(viewModel)

    var notesText by remember { mutableStateOf(state.note) }

    LaunchedEffect(Unit) {
        intent(SentenceDetailViewModel.Intent.InitWith(id))
    }

    Column(modifier = modifier) {

        AppToolbar(
            navigateUpIcon = Icons.Default.ArrowBack,
            title = "",
            onNavigateUp = navigateBack
        )

        Text(text = state.japanese)

        Text(text = state.english)

        NotesTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = notesText,
            onValueChanged = { notesText = it }
        )

    }
}