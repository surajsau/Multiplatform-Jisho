package `in`.surajsau.jisho.android.ui.reference.kanji

import `in`.surajsau.jisho.android.ui.components.AppToolbar
import `in`.surajsau.jisho.android.ui.reference.kanji.components.AllSchoolItem
import `in`.surajsau.jisho.android.ui.reference.kanji.components.KanjiGradeItem
import `in`.surajsau.jisho.android.ui.theme.sectionTitle
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun KanjiResourceScreen(
    modifier: Modifier = Modifier,
    navigateToGradeList: (grade: Int) -> Unit,
    navigateToAllGradesList: () -> Unit,
    navigateBack: () -> Unit,
) {
    Column(modifier = modifier) {

        AppToolbar(
            modifier = Modifier.fillMaxWidth(),
            navigateUpIcon = Icons.Default.ArrowBack,
            title = "Kanji",
            onNavigateUp = navigateBack
        )

        Column(
            modifier = Modifier
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
                style = MaterialTheme.typography.subtitle1,
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
                            .fillMaxWidth()
                            .clickable { navigateToGradeList(it) },
                        grade = "$it"
                    )
                }
            }

            Text(
                modifier = Modifier.alpha(0.6f),
                text = "中学校（7-9）",
                style = MaterialTheme.typography.subtitle1,
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
                            .width(56.dp)
                            .clickable { navigateToGradeList(grade) },
                        grade = "$grade"
                    )
                }
            }

            AllSchoolItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navigateToAllGradesList() },
            )
        }
    }
}