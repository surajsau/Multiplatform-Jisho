package `in`.surajsau.jisho.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.details.components.DetailsAlternative
import `in`.surajsau.jisho.details.components.DetailsConjugations
import `in`.surajsau.jisho.details.components.DetailsKanji
import `in`.surajsau.jisho.details.components.DetailsMeaning
import `in`.surajsau.jisho.details.components.DetailsSentence

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    state: DetailsScreenState,
) {
    val uiState = state.uiState

    Column(modifier = modifier) {
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
                    onItemClicked = state::onSentenceItemClicked,
                    onShowMoreClicked = state::onShowMoreClicked,
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
