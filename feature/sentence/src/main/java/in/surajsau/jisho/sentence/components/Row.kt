package `in`.surajsau.jisho.sentence.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.model.SentenceResult
import `in`.surajsau.jisho.ui.theme.PreviewContainer

@Composable
internal fun SentenceRow(
    modifier: Modifier = Modifier,
    model: SentenceResult,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = model.japanese,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(Modifier.height(4.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = model.english,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
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
private fun PreviewSentenceRow() {
    PreviewContainer {
        Box(modifier = Modifier.fillMaxWidth()) {
            SentenceRow(
                modifier = Modifier,
                model = SentenceResult(
                    id = 0,
                    japanese = "花火見た？",
                    english = "Did you see the fireworks?"
                ),
                onClick = {}
            )
        }
    }
}
