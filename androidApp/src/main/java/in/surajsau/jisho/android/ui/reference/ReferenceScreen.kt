package `in`.surajsau.jisho.android.ui.reference

import `in`.surajsau.jisho.android.ui.Navigation
import `in`.surajsau.jisho.android.ui.components.AppToolbar
import `in`.surajsau.jisho.android.ui.reference.components.ReferenceRow
import `in`.surajsau.jisho.model.KanjiQuery
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ReferenceScreen(
    modifier: Modifier = Modifier,
    onItemClicked: (Navigation.Resources) -> Unit,
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
                modifier = Modifier.fillMaxWidth()
                    .clickable { onItemClicked(Navigation.Resources.Kana) },
                color = Color(0xFFff784f),
                title = "Kana",
                description = "Hiragana & Katakana"
            )

            ReferenceRow(
                modifier = Modifier.fillMaxWidth()
                    .clickable { onItemClicked(Navigation.Resources.Jlpt) },
                color = Color(0xFFdb9d47),
                title = "Kanji",
                description = "Catalog of Kanji characters"
            )

            ReferenceRow(
                modifier = Modifier.fillMaxWidth()
                    .clickable { onItemClicked(Navigation.Resources.Jlpt) },
                color = Color(0xFF3185fc),
                title = "JLPT",
                description = "Vocabulary resources for Japanese Language Proficiency Test"
            )
        }
    }
}