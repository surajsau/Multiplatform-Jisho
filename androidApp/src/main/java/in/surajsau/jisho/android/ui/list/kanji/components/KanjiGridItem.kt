package `in`.surajsau.jisho.android.ui.list.kanji.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.surajsau.jisho.android.neomorphic.NeomorphicShape
import `in`.surajsau.jisho.android.neomorphic.neomorph
import `in`.surajsau.jisho.android.ui.theme.LightColors
import `in`.surajsau.jisho.model.KanjiResult

@Composable
fun KanjiGridItem(
    modifier: Modifier = Modifier,
    item: KanjiResult,
    onItemClicked: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .aspectRatio(1f)
            .clickable { onItemClicked() }
            .neomorph(shape = NeomorphicShape.RoundedCorner(8.dp)),
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

@Preview
@Composable
private fun previewKanjiGridItem() {
    MaterialTheme(colors = LightColors) {
        Surface {
            Box(modifier = Modifier.size(100.dp), contentAlignment = Alignment.Center) {
                KanjiGridItem(
                    modifier = Modifier.size(56.dp),
                    item = KanjiResult("楽")
                )
            }
        }
    }
}