package `in`.surajsau.jisho.details.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.ui.theme.AppTheme
import `in`.surajsau.jisho.ui.theme.sectionTitle

@Composable
internal fun DetailsAlternative(
    modifier: Modifier = Modifier,
    text: String,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Alternatives",
            style = MaterialTheme.typography.sectionTitle
        )

        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium
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
