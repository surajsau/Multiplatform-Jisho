package `in`.surajsau.jisho.reference.jlpt.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.ui.theme.JishoTheme
import `in`.surajsau.jisho.ui.theme.cardDescription
import `in`.surajsau.jisho.ui.theme.cardTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun JlptRow(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    onItemClicked: () -> Unit,
) {
    Card(
        modifier = modifier,
        onClick = onItemClicked,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.cardTitle,
                color = MaterialTheme.colorScheme.onSurface,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = description,
                style = MaterialTheme.typography.cardDescription,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
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
private fun PreviewJlptRowItem() {
    JishoTheme {
        JlptRow(
            modifier = Modifier.fillMaxWidth(),
            title = "N5",
            description = "Understanding of some basic Japanese",
            onItemClicked = {}
        )
    }
}
