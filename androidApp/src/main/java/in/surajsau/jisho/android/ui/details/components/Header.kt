package `in`.surajsau.jisho.android.ui.details.components

import `in`.surajsau.jisho.android.ui.theme.LightColors
import `in`.surajsau.jisho.viewmodel.DetailsViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DetailsHeader(
    modifier: Modifier = Modifier,
    model: DetailsViewModel.Header,
) {

    Column(modifier = modifier) {
        Text(
            text = model.onyomi,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Normal,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = model.kanji,
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview
@Composable
private fun PreviewDetailsHeader() {
    MaterialTheme(colors = LightColors) {
        Surface {
            Box(modifier = Modifier.fillMaxSize()) {
                DetailsHeader(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    model = DetailsViewModel.Header(
                        kanji = "利かん気",
                        onyomi = "きかんき",
                    )
                )
            }
        }
    }
}