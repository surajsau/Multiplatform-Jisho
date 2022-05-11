package `in`.surajsau.jisho.android.ui.reference.jlpt.components

import `in`.surajsau.jisho.android.neomorphic.neomorph
import `in`.surajsau.jisho.android.ui.theme.AppTheme
import `in`.surajsau.jisho.android.ui.theme.sectionTitle
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun JlptRow (
    modifier: Modifier = Modifier,
    title: String,
    description: String,
) {

    Column(
        modifier = modifier
            .neomorph()
            .padding(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.sectionTitle,
        )

        Text(
            text = description,
            style = MaterialTheme.typography.body1
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
private fun PreviewJlptRowItem() {
    AppTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            JlptRow(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                title = "N5",
                description = "Understanding of some basic Japanese"
            )
        }
    }
}