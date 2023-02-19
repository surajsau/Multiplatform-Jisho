package `in`.surajsau.jisho.sentence

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import `in`.surajsau.jisho.sentence.components.NotesTextField
import `in`.surajsau.jisho.ui.theme.components.JishoScaffold
import `in`.surajsau.jisho.viewmodel.SentenceDetailViewModel
import `in`.surajsau.jisho.ui.R
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SentenceDetailsScreen(
    modifier: Modifier = Modifier,
    id: Long,
    viewModel: SentenceDetailViewModel = koinViewModel(),
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    var notesText by remember { mutableStateOf(state.note) }

    LaunchedEffect(Unit) {
        viewModel.initWith(id)
    }

    JishoScaffold(
        modifier = Modifier
            .nestedScroll(connection = scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                onBackClicked = onBackClick
            )
        }
    ) { padding ->
        Column(modifier = modifier.padding(padding)) {
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
    onBackClicked: () -> Unit,
) {
    LargeTopAppBar(
        modifier = modifier,
        title = {},
        navigationIcon = {
            IconButton(onClick = onBackClicked) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Sentence Details Back"
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}
