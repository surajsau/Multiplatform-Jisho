package `in`.surajsau.jisho.android.ui.reference.kanji.components

import `in`.surajsau.jisho.android.neomorphic.neomorph
import `in`.surajsau.jisho.android.ui.theme.AppTheme
import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun KanjiGradeItem (
    modifier: Modifier = Modifier,
    grade: String,
) {
    Box(modifier = modifier
        .aspectRatio(1f)
        .neomorph()
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = grade,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun AllSchoolItem(modifier: Modifier) {
    Box(modifier = modifier
        .neomorph()
        .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = "All school",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold
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
private fun PreviewKanjiGradeItem() {
    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            KanjiGradeItem(
                modifier = Modifier.width(72.dp),
                grade = "1"
            )

            Spacer(Modifier.height(16.dp))

            AllSchoolItem(modifier = Modifier.fillMaxWidth())
        }
    }
}