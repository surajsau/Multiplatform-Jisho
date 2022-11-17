package `in`.surajsau.jisho.sentence.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import `in`.surajsau.jisho.utils.dispatch
import `in`.surajsau.jisho.sentence.details.components.NotesTextField
import `in`.surajsau.jisho.viewmodel.SentenceDetailViewModel
import org.koin.androidx.compose.get

@Composable
fun SentenceDetailsScreen(
    modifier: Modifier = Modifier,
    id: Long,
    viewModel: SentenceDetailViewModel = get()
) {
    val (state, intent, _) = dispatch(viewModel)

    var notesText by remember { mutableStateOf(state.note) }

    LaunchedEffect(Unit) {
        intent(SentenceDetailViewModel.Intent.InitWith(id))
    }

    Column(modifier = modifier) {
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
