package `in`.surajsau.jisho.details.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.ui.theme.sectionTitle
import `in`.surajsau.jisho.model.SentenceResult
import `in`.surajsau.jisho.ui.theme.PreviewContainer
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
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "Example sentences",
            style = MaterialTheme.typography.sectionTitle
        )

        model.items.forEach { item ->
            SentenceRow(
                japanese = item.japanese,
                english = item.english,
                onClick = { onItemClicked(item.id) }
            )
        }

        if (model.showMore) {
            TextButton(onClick = onShowMoreClicked) {
                Text(text = "Show more sentences")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SentenceRow(
    modifier: Modifier = Modifier,
    japanese: String,
    english: String,
    onClick: () -> Unit
) {
    ListItem(
        modifier = modifier
            .clickable(onClick = onClick),
        headlineText = {
            Text(
                text = japanese,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
        },
        supportingText = {
            Text(
                text = english,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    )
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
private fun PreviewDetailsSentence() {
    PreviewContainer {
        Surface {
            Box(modifier = Modifier.fillMaxWidth()) {
                DetailsSentence(
                    modifier = Modifier.fillMaxWidth(),
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
}
