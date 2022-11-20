package `in`.surajsau.jisho.reference.kanji

import android.content.res.Configuration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.reference.kanji.components.AllSchoolItem
import `in`.surajsau.jisho.reference.kanji.components.KanjiGradeItem
import `in`.surajsau.jisho.ui.theme.PreviewContainer

@Composable
fun KanjiResourceScreen(
    modifier: Modifier = Modifier,
    onGradeItemClicked: (grade: Int) -> Unit,
    onAllGradesClicked: () -> Unit
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 16.dp),
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Text(
                text = "小学校（1-6）",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        items(6) { grade ->
            KanjiGradeItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                grade = "${grade + 1}",
                onItemClicked = { onGradeItemClicked(grade) }
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "中学校（7-9）",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(16.dp))
        }


        items(3) {
            val grade = it + 6
            KanjiGradeItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                grade = "${grade + 1}",
                onItemClicked = { onGradeItemClicked(it) }
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))

            AllSchoolItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                onItemClicked = onAllGradesClicked
            )
        }

        item {
            Spacer(modifier = Modifier.height(72.dp))
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
private fun PreviewKanjiResourceScreen() {
    PreviewContainer {
        Surface {
            KanjiResourceScreen(
                modifier = Modifier.fillMaxSize(),
                onGradeItemClicked = {},
                onAllGradesClicked = {}
            )
        }
    }
}
