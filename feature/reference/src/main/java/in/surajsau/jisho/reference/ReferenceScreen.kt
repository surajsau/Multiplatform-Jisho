package `in`.surajsau.jisho.reference

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.navigation.NavigationItem
import `in`.surajsau.jisho.ui.component.AppToolbar
import `in`.surajsau.jisho.reference.components.ReferenceRow

@Composable
fun ReferenceScreen(
    modifier: Modifier = Modifier,
    onItemClicked: (NavigationItem.Resources) -> Unit,
) {
    Column(modifier = modifier) {
        AppToolbar(
            modifier = Modifier.fillMaxWidth(),
            title = "Resources"
        )

        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(all = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            ReferenceRow(
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFFff784f),
                title = "Kana",
                description = "Hiragana & Katakana",
                onItemClicked = { onItemClicked(NavigationItem.Resources.Kana) }
            )

            ReferenceRow(
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFFdb9d47),
                title = "Kanji",
                description = "Catalog of Kanji characters",
                onItemClicked = { onItemClicked(NavigationItem.Resources.Kanji) }
            )

            ReferenceRow(
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFF3185fc),
                title = "JLPT",
                description = "Vocabulary resources for Japanese Language Proficiency Test",
                onItemClicked = { onItemClicked(NavigationItem.Resources.Jlpt) }
            )
        }
    }
}
