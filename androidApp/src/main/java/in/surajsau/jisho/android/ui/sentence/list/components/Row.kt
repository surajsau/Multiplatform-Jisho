package `in`.surajsau.jisho.android.ui.sentence.list.components

import `in`.surajsau.jisho.android.neomorphic.neomorph
import `in`.surajsau.jisho.android.ui.theme.AppTheme
import `in`.surajsau.jisho.model.SentenceResult
import android.content.res.Configuration
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
fun SentenceRow (
    modifier: Modifier = Modifier,
    model: SentenceResult,
) {
    Column(
        modifier = modifier
            .neomorph(animatePress = true)
            .padding(horizontal = 16.dp, vertical = 12.dp),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = model.japanese,
            style = MaterialTheme.typography.body2,
            fontWeight = FontWeight.SemiBold,
        )

        Spacer(Modifier.height(4.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .alpha(0.6f),
            text = model.english,
            style = MaterialTheme.typography.body2,
        )
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
private fun PreviewSentenceRow() {
    AppTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            SentenceRow(
                modifier = Modifier,
                model = SentenceResult(
                    id = 0,
                    japanese = "",
                    english = ""
                )
            )
        }
    }
}