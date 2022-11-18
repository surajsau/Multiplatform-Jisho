package `in`.surajsau.jisho.reference.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.ui.theme.JishoTheme
import `in`.surajsau.jisho.ui.theme.cardDescription
import `in`.surajsau.jisho.ui.theme.cardTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReferenceRow(
    modifier: Modifier = Modifier,
    color: Color,
    title: String,
    description: String,
    onItemClicked: () -> Unit,
) {
    Card(
        modifier = modifier,
        onClick = onItemClicked
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(size = 48.dp)
                    .background(color = color, shape = CircleShape)
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceAround,
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = title,
                    style = MaterialTheme.typography.cardTitle
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = description,
                    style = MaterialTheme.typography.cardDescription,
                    maxLines = 2
                )
            }
        }
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
private fun PreviewReferenceRow() {
    JishoTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ReferenceRow(
                modifier = Modifier.fillMaxWidth(),
                color = Color.Red,
                title = "Kana",
                description = "Japanese syllables (Hiragana & Katakana)"
            ) {}

            ReferenceRow(
                modifier = Modifier.fillMaxWidth(),
                color = Color.Green,
                title = "JLPT",
                description = "Standardized test to evaluate language proficiency for non-native frequency"
            ) {}
        }
    }
}
