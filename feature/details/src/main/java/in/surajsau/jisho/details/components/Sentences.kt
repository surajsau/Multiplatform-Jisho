package `in`.surajsau.jisho.details.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.ui.neomorphic.clickableNeomorph
import `in`.surajsau.jisho.ui.neomorphic.neomorph
import `in`.surajsau.jisho.ui.theme.sectionTitle
import `in`.surajsau.jisho.model.SentenceResult
import `in`.surajsau.jisho.viewmodel.DetailsViewModel

@Composable
internal fun DetailsSentence(
    modifier: Modifier = Modifier,
    model: DetailsViewModel.Sentences,
    onItemClicked: (Long) -> Unit,
    onShowMoreClicked: () -> Unit,
) {
    Column(modifier = modifier) {
        Text(
            text = "Example sentences",
            style = MaterialTheme.typography.sectionTitle
        )

        Spacer(Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            model.items.forEach { item ->
                Column(
                    modifier = Modifier
                        .clickableNeomorph { onItemClicked(item.id) }
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = item.japanese,
                        style = MaterialTheme.typography.body2,
                        fontWeight = FontWeight.SemiBold,
                    )

                    Spacer(Modifier.height(4.dp))

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .alpha(0.6f),
                        text = item.english,
                        style = MaterialTheme.typography.body2,
                    )
                }
            }

            if (model.showMore) {
                Text(
                    modifier = Modifier
                        .clickable { onShowMoreClicked() }
                        .fillMaxWidth()
                        .neomorph()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    text = "Show more sentences...",
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
    }
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun PreviewDetailsSentence() {
    `in`.surajsau.jisho.ui.theme.AppTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            DetailsSentence(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                model = DetailsViewModel.Sentences(
                    items = listOf(
                        SentenceResult(
                            id = 0,
                            japanese = "私の時計はきちんと動いている。",
                            english = "My watch is running all right."
                        ),
                        SentenceResult(
                            id = 1,
                            japanese = "自分の部屋は出来るだけきちんとしておきたい。",
                            english = "I want to keep my room as neat as possible."
                        )
                    ),
                    sentenceCount = 10
                ),
                onItemClicked = {},
                onShowMoreClicked = {}
            )
        }
    }
}
