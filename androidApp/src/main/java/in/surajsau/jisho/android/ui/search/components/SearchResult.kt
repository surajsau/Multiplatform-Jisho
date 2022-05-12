package `in`.surajsau.jisho.android.ui.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.surajsau.jisho.android.R
import `in`.surajsau.jisho.android.neomorphic.clickableNeomorph
import `in`.surajsau.jisho.android.ui.theme.DarkColors
import `in`.surajsau.jisho.android.ui.theme.LightColors
import `in`.surajsau.jisho.model.SearchResult

@Composable
fun SearchResultRow(
    modifier: Modifier = Modifier,
    result: SearchResult,
    onItemClicked: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .clickableNeomorph { onItemClicked() }
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.Bottom,
        ) {
            Text(
                text = result.value,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = result.reading,
                fontSize = 13.sp,
                color = colorResource(id = R.color.colorAccent)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .alpha(0.7f),
            text = result.meanings,
            fontSize = 13.sp,
        )

        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Preview(name = "Light")
@Composable
private fun previewSearchResult() {
    MaterialTheme(colors = LightColors) {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                SearchResultRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    result = SearchResult(
                        id = 0L,
                        value = "楽しい",
                        reading = "たのしい",
                        meanings = "fun・to have fun・enjoy"
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                SearchResultRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    result = SearchResult(
                        id = 0L,
                        value = "楽しい",
                        reading = "たのしい",
                        meanings = "fun・to have fun・enjoy"
                    )
                )
            }
        }
    }
}

@Preview("Dark")
@Composable
private fun previewSearchResultDark() {
    MaterialTheme(colors = DarkColors) {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                SearchResultRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    result = SearchResult(
                        id = 0L,
                        value = "楽しい",
                        reading = "たのしい",
                        meanings = "fun・to have fun・enjoy"
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                SearchResultRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    result = SearchResult(
                        id = 0L,
                        value = "楽しい",
                        reading = "たのしい",
                        meanings = "fun・to have fun・enjoy"
                    )
                )
            }
        }
    }
}
