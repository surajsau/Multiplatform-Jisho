package `in`.surajsau.jisho.android.ui.details.components

import `in`.surajsau.jisho.android.ui.theme.AppTheme
import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DetailsAlternative (
    modifier: Modifier = Modifier,
    text: String,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Alternatives",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Medium
        )

        Text(
            text = text,
            style = MaterialTheme.typography.body2
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
private fun PreviewDetailsAlternative() {
    AppTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            DetailsAlternative(
                modifier = Modifier,
                text = "きかん気, 聞かん気"
            )
        }
    }
}