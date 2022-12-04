package `in`.surajsau.jisho.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.details.components.DetailsAlternative
import `in`.surajsau.jisho.details.components.DetailsConjugations
import `in`.surajsau.jisho.details.components.DetailsKanji
import `in`.surajsau.jisho.details.components.DetailsMeaning
import `in`.surajsau.jisho.details.components.DetailsSentence
import `in`.surajsau.jisho.details.components.TopAppBar
import `in`.surajsau.jisho.ui.theme.components.JishoScaffold

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
                bucket = uiState.bucket,
                scrollBehavior = scrollBehavior,
                onBackClicked = onBackClick,
                onLikeClicked = state::onLikeClicked
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth()
                .verticalScroll(state = state.scrollState),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = uiState.header.onyomi,
                style = MaterialTheme.typography.titleMedium
            )

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
