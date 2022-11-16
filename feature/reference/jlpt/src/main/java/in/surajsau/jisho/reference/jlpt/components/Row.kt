package `in`.surajsau.jisho.reference.jlpt.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.neumorphic.clickableNeomorph
import `in`.surajsau.jisho.ui.theme.AppTheme
import `in`.surajsau.jisho.ui.theme.cardDescription
import `in`.surajsau.jisho.ui.theme.cardTitle

@Composable
internal fun JlptRow(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    onItemClicked: () -> Unit,
) {
    Column(
        modifier = modifier
            .clickableNeomorph(onClick = onItemClicked)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.cardTitle,
        )

        Text(
            modifier = Modifier.alpha(0.6f),
            text = description,
            style = MaterialTheme.typography.cardDescription
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
private fun PreviewJlptRowItem() {
    AppTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            JlptRow(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                title = "N5",
                description = "Understanding of some basic Japanese"
            ) {}
        }
    }
}
