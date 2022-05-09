package `in`.surajsau.jisho.android.ui.reference.kanji

import `in`.surajsau.jisho.android.neomorphic.neomorph
import `in`.surajsau.jisho.android.ui.components.AppToolbar
import `in`.surajsau.jisho.android.ui.reference.kanji.components.AllSchoolItem
import `in`.surajsau.jisho.android.ui.reference.kanji.components.KanjiGradeItem
import `in`.surajsau.jisho.android.ui.theme.sectionTitle
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun KanjiResourceScreen(
    modifier: Modifier = Modifier,
    navigateToGradeList: (from: Int, to: Int) -> Unit,
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
                .padding(all = 16.dp)
        ) {
            Text(
                text = "Grades",
                style = MaterialTheme.typography.sectionTitle
            )

            Spacer(Modifier.height(24.dp))

            Text(
                text = "小学校（1-6）",
                style = MaterialTheme.typography.subtitle1
            )

            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                items(6) {
                    KanjiGradeItem(
                        modifier = Modifier
                            .width(72.dp)
                            .clickable { navigateToGradeList(it, it) },
                        grade = "$it"
                    )
                }
            }

            Text(
                text = "中学校（7-9）",
                style = MaterialTheme.typography.subtitle1
            )

            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                items(3) {
                    val grade = it + 7
                    KanjiGradeItem(
                        modifier = Modifier
                            .width(56.dp)
                            .clickable { navigateToGradeList(grade, grade) },
                        grade = "$grade"
                    )
                }
            }

            AllSchoolItem(
                modifier = Modifier.fillMaxWidth()
                    .clickable { navigateToGradeList(1, 10) },
            )
        }
    }
}