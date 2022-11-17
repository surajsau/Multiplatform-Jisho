package `in`.surajsau.jisho.sentence.list.components

import android.content.res.Configuration
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
import `in`.surajsau.jisho.model.SentenceResult
import `in`.surajsau.jisho.ui.theme.JishoTheme

@Composable
internal fun SentenceRow(
    modifier: Modifier = Modifier,
    model: SentenceResult,
) {
    Column(
        modifier = modifier
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
    JishoTheme {
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
