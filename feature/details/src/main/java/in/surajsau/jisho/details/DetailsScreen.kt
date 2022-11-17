package `in`.surajsau.jisho.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.details.components.DetailsAlternative
import `in`.surajsau.jisho.details.components.DetailsConjugations
import `in`.surajsau.jisho.details.components.DetailsHeader
import `in`.surajsau.jisho.details.components.DetailsKanji
import `in`.surajsau.jisho.details.components.DetailsMeaning
import `in`.surajsau.jisho.details.components.DetailsSentence
import `in`.surajsau.jisho.details.model.DetailsModel
import `in`.surajsau.jisho.utils.dispatch
import `in`.surajsau.jisho.viewmodel.DetailsViewModel
import org.koin.androidx.compose.get

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    model: DetailsModel,
    viewModel: DetailsViewModel = get(),
    navigateToSentenceDetails: (Long) -> Unit,
    navigateToSentenceList: (String) -> Unit,
) {
    val (state, intent, _) = dispatch(viewModel)

    LaunchedEffect(model) {
        intent(DetailsViewModel.Intent.InitWith(model.id, model.word))
    }

    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(state = rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            DetailsHeader(
                modifier = Modifier.fillMaxWidth(),
                model = state.header
            )

            if (state.showAlternative) {
                DetailsAlternative(
                    modifier = Modifier.fillMaxWidth(),
                    text = state.alternatives
                )
            }

            if (state.showMeanings) {
                DetailsMeaning(
                    modifier = Modifier.fillMaxWidth(),
                    items = state.meanings
                )
            }

            if (state.showKanji) {
                DetailsKanji(
                    modifier = Modifier.fillMaxWidth(),
                    items = state.kanjis
                ) { kanji -> // do nothing
                }
            }

            if (state.showSentences) {
                DetailsSentence(
                    modifier = Modifier.fillMaxWidth(),
                    model = state.sentences,
                    onItemClicked = navigateToSentenceDetails,
                    onShowMoreClicked = { navigateToSentenceList(model.word) },
                )
            }

            if (state.showConjugation) {
                DetailsConjugations(
                    modifier = Modifier.fillMaxWidth(),
                    model = state.conjugations!!
                )
            }

            Spacer(Modifier.height(36.dp))
        }
    }
}
