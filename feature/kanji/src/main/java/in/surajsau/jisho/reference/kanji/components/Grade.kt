package `in`.surajsau.jisho.reference.kanji.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.ui.theme.PreviewContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun KanjiGradeItem(
    modifier: Modifier = Modifier,
    grade: String,
    onItemClicked: () -> Unit
) {
    Card(
        modifier = modifier,
        onClick = onItemClicked
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "${grade}年",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllSchoolItem(
    modifier: Modifier,
    onItemClicked: () -> Unit,
) {
    Card(
        modifier = modifier,
        onClick = onItemClicked
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "All school",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
private fun PreviewKanjiGradeItem() {
    PreviewContainer {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                KanjiGradeItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    grade = "1",
                    onItemClicked = {}
                )

                Spacer(Modifier.height(16.dp))

                AllSchoolItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    onItemClicked = {}
                )
            }
        }
    }
}
