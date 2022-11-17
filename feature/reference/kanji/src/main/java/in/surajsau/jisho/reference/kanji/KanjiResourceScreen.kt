package `in`.surajsau.jisho.reference.kanji

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.reference.kanji.components.AllSchoolItem
import `in`.surajsau.jisho.reference.kanji.components.KanjiGradeItem
import `in`.surajsau.jisho.ui.theme.sectionTitle

@Composable
fun KanjiResourceScreen(
    modifier: Modifier = Modifier,
    navigateToGradeList: (grade: Int) -> Unit,
    navigateToAllGradesList: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 16.dp),
    ) {
        Text(
            text = "Grades",
            style = MaterialTheme.typography.sectionTitle
        )

        Spacer(Modifier.height(24.dp))

        Text(
            modifier = Modifier.alpha(0.6f),
            text = "小学校（1-6）",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold
        )

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            columns = GridCells.Adaptive(minSize = 72.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(6) {
                KanjiGradeItem(
                    modifier = Modifier
                        .fillMaxWidth(),
                    grade = "$it",
                    onItemClicked = { navigateToGradeList(it) }
                )
            }
        }

        Text(
            modifier = Modifier.alpha(0.6f),
            text = "中学校（7-9）",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold
        )

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            columns = GridCells.Adaptive(minSize = 72.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(3) {
                val grade = it + 7
                KanjiGradeItem(
                    modifier = Modifier
                        .width(56.dp),
                    grade = "$grade",
                    onItemClicked = { navigateToGradeList(grade) }
                )
            }
        }

        AllSchoolItem(
            modifier = Modifier.fillMaxWidth(),
            onItemClicked = { navigateToAllGradesList() }
        )
    }
}
