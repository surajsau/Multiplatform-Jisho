package `in`.surajsau.jisho.reference.kanji

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.model.KanjiGrade
import `in`.surajsau.jisho.reference.kanji.components.AllSchoolItem
import `in`.surajsau.jisho.reference.kanji.components.KanjiGradeItem
import `in`.surajsau.jisho.ui.R
import `in`.surajsau.jisho.ui.theme.PreviewContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KanjiResourceScreen(
    modifier: Modifier = Modifier,
    onGradeItemClicked: (grade: Int) -> Unit,
    onAllGradesClicked: () -> Unit,
    onBackClicked: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = { Text(text = "Kanji") },
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "back"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(state = rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = "小学校（1-6）",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            arrayOf(
                KanjiGrade.Grade1,
                KanjiGrade.Grade2,
                KanjiGrade.Grade3,
                KanjiGrade.Grade4,
                KanjiGrade.Grade5,
                KanjiGrade.Grade6,
            ).forEach { grade ->
                KanjiGradeItem(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .height(56.dp),
                    grade = grade.title,
                    onItemClicked = { onGradeItemClicked(grade.grade) }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = "中学校（7-9）",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            arrayOf(
                KanjiGrade.Grade7,
                KanjiGrade.Grade8,
                KanjiGrade.Grade9,
            ).forEach { grade ->
                KanjiGradeItem(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .height(56.dp),
                    grade = grade.title,
                    onItemClicked = { onGradeItemClicked(grade.grade) }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            AllSchoolItem(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .height(56.dp),
                onItemClicked = onAllGradesClicked
            )

            Spacer(modifier = Modifier.height(24.dp))
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
                onAllGradesClicked = {},
                onBackClicked = {}
            )
        }
    }
}
