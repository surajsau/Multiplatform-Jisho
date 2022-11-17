package `in`.surajsau.jisho.search.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.model.SearchResult
import `in`.surajsau.jisho.search.R
import `in`.surajsau.jisho.ui.theme.JishoTheme
import `in`.surajsau.jisho.ui.theme.cardDescription
import `in`.surajsau.jisho.ui.theme.cardTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ResultRow(
    modifier: Modifier = Modifier,
    result: SearchResult,
    onItemClicked: () -> Unit
) {
    Card(
        modifier = modifier,
        onClick = onItemClicked
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
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.cardTitle
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = result.reading,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.cardTitle
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = result.meanings,
            style = MaterialTheme.typography.cardDescription,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(12.dp))
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
private fun previewSearchResult() {
    JishoTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            ResultRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                result = SearchResult(
                    id = 0L,
                    value = "楽しい",
                    reading = "たのしい",
                    meanings = "fun・to have fun・enjoy"
                ),
                onItemClicked = {}
            )

            Spacer(Modifier.height(16.dp))

            ResultRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                result = SearchResult(
                    id = 0L,
                    value = "楽しい",
                    reading = "たのしい",
                    meanings = "fun・to have fun・enjoy"
                ),
                onItemClicked = {}
            )
        }
    }
}