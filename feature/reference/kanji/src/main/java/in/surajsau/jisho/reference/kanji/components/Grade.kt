package `in`.surajsau.jisho.reference.kanji.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.ui.theme.JishoTheme

@Composable
internal fun KanjiGradeItem(
    modifier: Modifier = Modifier,
    grade: String,
    onItemClicked: () -> Unit
) {
    Box(modifier = modifier.aspectRatio(1f)) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = grade,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun AllSchoolItem(
    modifier: Modifier,
    onItemClicked: () -> Unit,
) {
    Box(modifier = modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = "All school",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
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
private fun PreviewKanjiGradeItem() {
    JishoTheme {
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
            ) {}

            Spacer(Modifier.height(16.dp))

            AllSchoolItem(modifier = Modifier.fillMaxWidth()) {}
        }
    }
}
