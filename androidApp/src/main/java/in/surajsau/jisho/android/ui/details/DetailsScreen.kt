package `in`.surajsau.jisho.android.ui.details

import `in`.surajsau.jisho.android.base.dispatch
import `in`.surajsau.jisho.android.ui.components.AppToolbar
import `in`.surajsau.jisho.android.ui.details.components.*
import `in`.surajsau.jisho.android.ui.details.model.DetailsModel
import `in`.surajsau.jisho.viewmodel.DetailsViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.get

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    model: DetailsModel,
    viewModel: DetailsViewModel = get(),
    navigateToSentenceDetails: (Long) -> Unit,
    navigateToSentenceList: (String) -> Unit,
    navigateBack: () -> Unit = {},
) {

    val (state, intent, _) = dispatch(viewModel)

    LaunchedEffect(model) {
        intent(DetailsViewModel.Intent.InitWith(model.id, model.word))
    }

    Column(modifier = modifier) {

        AppToolbar(
            modifier = Modifier.fillMaxWidth(),
            title = "",
            navigateUpIcon = Icons.Default.ArrowBack,
            onNavigateUp = navigateBack
        )

        Column(
            modifier = modifier
                .padding(horizontal = 16.dp)
                .weight(1f)
        ) {
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
}