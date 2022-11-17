package `in`.surajsau.jisho.reference.kanji.list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.surajsau.jisho.model.KanjiResult
import `in`.surajsau.jisho.ui.theme.JishoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KanjiGridItem(
    modifier: Modifier = Modifier,
    item: KanjiResult,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier,
        onClick = onClick
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = item.value,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview
@Composable
private fun previewKanjiGridItem() {
    JishoTheme {
        Surface {
            Box(modifier = Modifier.size(100.dp), contentAlignment = Alignment.Center) {
                KanjiGridItem(
                    modifier = Modifier.size(56.dp),
                    item = KanjiResult("楽"),
                    onClick = {}
                )
            }
        }
    }
}
