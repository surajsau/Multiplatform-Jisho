package `in`.surajsau.jisho.android.ui.reference.components

import `in`.surajsau.jisho.android.neomorphic.neomorph
import `in`.surajsau.jisho.android.ui.theme.AppTheme
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessAlarm
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ReferenceRow (
    modifier: Modifier = Modifier,
    color: Color,
    title: String,
    description: String
) {

    Column(
        modifier = modifier
            .height(72.dp)
            .neomorph()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxHeight()
                    .background(color = color, shape = CircleShape)
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceAround,
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = title,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(0.6f),
                    text = description,
                    style = MaterialTheme.typography.subtitle2,
                    maxLines = 2
                )
            }
        }
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
private fun PreviewReferenceRow() {
    AppTheme {
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
            )

            ReferenceRow(
                modifier = Modifier.fillMaxWidth(),
                color = Color.Green,
                title = "JLPT",
                description = "Standardized test to evaluate language proficiency for non-native frequency"
            )
        }
    }
}