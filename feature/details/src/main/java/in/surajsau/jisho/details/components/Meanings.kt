package `in`.surajsau.jisho.details.components

import android.content.res.Configuration
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.ui.neomorphic.neomorph
import `in`.surajsau.jisho.ui.theme.sectionTitle
import `in`.surajsau.jisho.viewmodel.DetailsViewModel

@Composable
internal fun DetailsMeaning(
    modifier: Modifier = Modifier,
    items: List<DetailsViewModel.Meaning>,
) {
    Column(modifier = modifier) {
        Text(
            text = "Meanings",
            style = MaterialTheme.typography.sectionTitle
        )

        Spacer(Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items.forEach { item ->
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        modifier = Modifier.alpha(0.6f),
                        text = item.pos,
                        style = MaterialTheme.typography.body2,
                    )

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = item.value,
                        style = MaterialTheme.typography.body1,
                    )

                    Spacer(Modifier.height(8.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(4.dp)
                            .neomorph(
                                isPressed = true,
                                elevation = 2.dp,
                            ),
                    )
                }
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
private fun PreviewMeanings() {
    `in`.surajsau.jisho.ui.theme.AppTheme {
        DetailsMeaning(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
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
