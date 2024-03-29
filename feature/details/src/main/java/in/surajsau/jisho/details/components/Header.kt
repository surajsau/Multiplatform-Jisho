package `in`.surajsau.jisho.details.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.ui.theme.JishoTheme
import `in`.surajsau.jisho.viewmodel.DetailsViewModel

@Composable
internal fun DetailsHeader(
    modifier: Modifier = Modifier,
    model: DetailsViewModel.Header,
) {
    Column(modifier = modifier) {
        Text(
            text = model.onyomi,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Normal,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = model.kanji,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.SemiBold
        )
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
private fun PreviewDetailsHeader() {
    JishoTheme {
        DetailsHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            model = DetailsViewModel.Header(
                kanji = "利かん気",
                onyomi = "きかんき",
            )
        )
    }
}
