package `in`.surajsau.jisho.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.details.components.DetailsAlternative
import `in`.surajsau.jisho.details.components.DetailsConjugations
import `in`.surajsau.jisho.details.components.DetailsKanji
import `in`.surajsau.jisho.details.components.DetailsMeaning
import `in`.surajsau.jisho.details.components.DetailsSentence
import `in`.surajsau.jisho.ui.theme.components.JishoScaffold
import `in`.surajsau.jisho.ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    state: DetailsScreenState,
    onShowMoreSentenceClick: (String) -> Unit,
    onSentenceItemClick: (Long) -> Unit,
    onBackClick: () -> Unit
) {
    val uiState = state.uiState

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    JishoScaffold(
        modifier = modifier
            .nestedScroll(connection = scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = uiState.header.kanji,
                scrollBehavior = scrollBehavior,
                onBackClicked = onBackClick
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .verticalScroll(state = state.scrollState),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                if (uiState.showAlternative) {
                    DetailsAlternative(
                        modifier = Modifier.fillMaxWidth(),
                        text = uiState.alternatives
                    )
                }

                if (uiState.showMeanings) {
                    DetailsMeaning(
                        modifier = Modifier.fillMaxWidth(),
                        items = uiState.meanings
                    )
                }

                if (uiState.showKanji) {
                    DetailsKanji(
                        modifier = Modifier.fillMaxWidth(),
                        items = uiState.kanjis,
                        onItemClicked = {}
                    )
                }

                if (uiState.showSentences) {
                    DetailsSentence(
                        modifier = Modifier.fillMaxWidth(),
                        model = uiState.sentences,
                        onItemClicked = onSentenceItemClick,
                        onShowMoreClicked = { onShowMoreSentenceClick(state.model.word) },
                    )
                }

                if (uiState.showConjugation) {
                    DetailsConjugations(
                        modifier = Modifier.fillMaxWidth(),
                        model = uiState.conjugations!!
                    )
                }

                Spacer(Modifier.height(36.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    scrollBehavior: TopAppBarScrollBehavior,
    onBackClicked: () -> Unit,
) {
    LargeTopAppBar(
        modifier = modifier,
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onBackClicked) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Details Back"
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}
