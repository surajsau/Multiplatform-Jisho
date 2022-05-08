package `in`.surajsau.jisho.android.ui.details.components

import `in`.surajsau.jisho.android.neomorphic.neomorph
import `in`.surajsau.jisho.android.ui.theme.AppTheme
import `in`.surajsau.jisho.viewmodel.DetailsViewModel
import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DetailsKanji (
    modifier: Modifier = Modifier,
    items: List<DetailsViewModel.KanjiItem>,
    onItemClicked: (DetailsViewModel.KanjiItem) -> Unit,
) {

    Column(modifier = modifier) {
        Text(
            text = "Kanjis in the word",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Medium
        )

        Spacer(Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items.forEach { item ->
                Row(modifier = Modifier
                    .clickable { onItemClicked(item) }
                    .fillMaxWidth()
                    .neomorph(animatePress = true)
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        modifier = Modifier.alpha(0.6f),
                        text = item.value,
                        style = MaterialTheme.typography.body2,
                        maxLines = 1,
                    )

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = item.meaning,
                        style = MaterialTheme.typography.body2,
                        maxLines = 1,
                    )
                }
            }
        }
    }
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun PreviewKanjis() {
    AppTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            DetailsKanji(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
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
            ){}
        }
    }
}