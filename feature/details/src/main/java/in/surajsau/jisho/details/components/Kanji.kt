package `in`.surajsau.jisho.details.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.ui.theme.JishoTheme
import `in`.surajsau.jisho.ui.theme.PreviewContainer
import `in`.surajsau.jisho.ui.theme.sectionTitle
import `in`.surajsau.jisho.viewmodel.DetailsViewModel

@Composable
internal fun DetailsKanji(
    modifier: Modifier = Modifier,
    items: List<DetailsViewModel.KanjiItem>,
    onItemClicked: (DetailsViewModel.KanjiItem) -> Unit,
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "Kanjis in the word",
            style = MaterialTheme.typography.sectionTitle
        )

        Column {
            items.forEach { item ->
                KanjiRow(
                    value = item.value,
                    meaning = item.meaning,
                    onClick = { onItemClicked(item) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun KanjiRow(
    modifier: Modifier = Modifier,
    value: String,
    meaning: String,
    onClick: () -> Unit
) {
    ListItem(
        modifier = modifier
            .clickable(onClick = onClick),
        headlineText = {
            Text(
                text = meaning,
                style = MaterialTheme.typography.bodyLarge,
            )
        },
        leadingContent = {
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
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
private fun PreviewKanjis() {
    PreviewContainer {
        Surface {
            Box(modifier = Modifier.fillMaxWidth()) {
                DetailsKanji(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    items = listOf(
                        DetailsViewModel.KanjiItem(value = "鯵", meaning = "horse mackerel"),
                        DetailsViewModel.KanjiItem(
                            value = "暗",
                            meaning = "darkness, shade, informal, grow dark, be blinded"
                        ),
                        DetailsViewModel.KanjiItem(
                            value = "安",
                            meaning = "relax, cheap, low, quiet, contentded, peaceful"
                        )
                    )
                ) {}
            }
        }
    }
}
