package `in`.surajsau.jisho.android.ui.details.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.android.ui.theme.AppTheme
import `in`.surajsau.jisho.android.ui.theme.sectionTitle
import `in`.surajsau.jisho.viewmodel.DetailsViewModel

@Composable
fun DetailsConjugations(
    modifier: Modifier = Modifier,
    model: DetailsViewModel.Conjugation,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Conjugations (formal)",
            style = MaterialTheme.typography.sectionTitle
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ConjugationRow(
                modifier = Modifier.fillMaxWidth(),
                title = "Long",
                value = model.presentFormal
            )

            ConjugationRow(
                modifier = Modifier.fillMaxWidth(),
                title = "Negative",
                value = model.presentFormalNegative
            )

            ConjugationRow(
                modifier = Modifier.fillMaxWidth(),
                title = "Past",
                value = model.pastFormal
            )

            ConjugationRow(
                modifier = Modifier.fillMaxWidth(),
                title = "Negative Past",
                value = model.pastFormalNegative
            )
        }

        Text(
            text = "Conjugations (informal)",
            style = MaterialTheme.typography.sectionTitle
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ConjugationRow(
                modifier = Modifier.fillMaxWidth(),
                title = "Long",
                value = model.presentInformal
            )

            ConjugationRow(
                modifier = Modifier.fillMaxWidth(),
                title = "Negative",
                value = model.presentInformalNegative
            )

            ConjugationRow(
                modifier = Modifier.fillMaxWidth(),
                title = "Past",
                value = model.pastInformal
            )

            ConjugationRow(
                modifier = Modifier.fillMaxWidth(),
                title = "Negative Past",
                value = model.pastInformalNegative
            )
        }

        Text(
            text = "Other conjugations",
            style = MaterialTheme.typography.sectionTitle
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ConjugationRow(
                modifier = Modifier.fillMaxWidth(),
                title = "Te",
                value = model.te
            )

            if (model.showCausative) {
                ConjugationRow(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Imperative",
                    value = model.causative
                )
            }

            if (model.showVolitional) {
                ConjugationRow(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Volitional",
                    value = model.volitional
                )
            }

            if (model.showHypothetical) {
                ConjugationRow(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Hypothetical",
                    value = model.hypothetical
                )
            }

            if (model.showPotential) {
                ConjugationRow(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Potential",
                    value = model.potential
                )
            }
        }
    }
}

@Composable
private fun ConjugationRow(
    modifier: Modifier = Modifier,
    title: String,
    value: String
) {
    Row(modifier = modifier) {
        Text(
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .alpha(0.6f),
            style = MaterialTheme.typography.body2,
            text = title,
            fontWeight = FontWeight.SemiBold,
        )

        Text(
            modifier = Modifier
                .fillMaxWidth(0.6f),
            style = MaterialTheme.typography.body2,
            text = value,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun PreviewDetailsConjugations() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            DetailsConjugations(
                modifier = Modifier.fillMaxSize(),
                model = DetailsViewModel.Conjugation(
                    presentFormal = "怒ります",
                    presentFormalNegative = "怒りません",
                    pastFormal = "怒りました",
                    pastFormalNegative = "怒りませんでした",
                    presentInformal = "怒る",
                    presentInformalNegative = "怒らない",
                    pastInformal = "怒った",
                    pastInformalNegative = "怒りなかった",
                    te = "怒って"
                ).apply {
                }
            )
        }
    }
}
