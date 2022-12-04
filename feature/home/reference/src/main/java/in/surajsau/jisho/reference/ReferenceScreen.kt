package `in`.surajsau.jisho.reference

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.reference.components.ReferenceRow

@Composable
fun ReferenceScreen(
    modifier: Modifier = Modifier,
    onKanaTapped: () -> Unit,
    onKanjiTapped: () -> Unit,
    onJlptTapped: () -> Unit
) {
    Column(
        modifier = modifier.padding(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        ReferenceRow(
            modifier = Modifier.fillMaxWidth(),
            icon = { Text(text = "あ", color = Color.White) },
            color = Color(0xFFff784f),
            title = "Kana",
            description = "Hiragana & Katakana",
            onItemClicked = onKanaTapped
        )

        ReferenceRow(
            modifier = Modifier.fillMaxWidth(),
            icon = { Text(text = "字", color = Color.White) },
            color = Color(0xFFdb9d47),
            title = "Kanji",
            description = "Catalog of Kanji characters",
            onItemClicked = onKanjiTapped
        )

        ReferenceRow(
            modifier = Modifier.fillMaxWidth(),
            icon = { Text(text = "N1", color = Color.White) },
            color = Color(0xFF3185fc),
            title = "JLPT",
            description = "Vocabulary resources for Japanese Language Proficiency Test",
            onItemClicked = onJlptTapped
        )
    }
}
