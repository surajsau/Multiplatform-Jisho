package `in`.surajsau.jisho.details.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.ui.theme.PreviewContainer
import `in`.surajsau.jisho.ui.theme.sectionTitle
import `in`.surajsau.jisho.viewmodel.DetailsViewModel

@Composable
internal fun DetailsMeaning(
    modifier: Modifier = Modifier,
    items: List<DetailsViewModel.Meaning>,
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "Meanings",
            style = MaterialTheme.typography.sectionTitle
        )

        Column {
            items.forEach { item ->
                MeaningRow(
                    particleOfSpeech = item.pos,
                    meaning = item.value,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MeaningRow(
    modifier: Modifier = Modifier,
    particleOfSpeech: String,
    meaning: String
) {
    ListItem(
        modifier = modifier,
        headlineText = {
            Text(
                text = particleOfSpeech,
                style = MaterialTheme.typography.titleMedium
            )
        },
        supportingText = {
            Text(
                text = meaning,
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
private fun PreviewMeanings() {
    PreviewContainer {
        Surface {
            DetailsMeaning(
                modifier = Modifier
                    .fillMaxWidth(),
                items = listOf(
                    DetailsViewModel.Meaning(pos = "Intonation", value = "that's too bad"),
                    DetailsViewModel.Meaning(pos = "Adverb", value = "with a clatter, with a rattle"),
                    DetailsViewModel.Meaning(
                        pos = "Noun",
                        value = "rough (personality, speech, etc.), unreserved, outspoken, boorish, ill-mannered"
                    )
                )
            )
        }
    }
}
