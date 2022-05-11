package `in`.surajsau.jisho.android.ui.reference.jlpt

import `in`.surajsau.jisho.android.ui.components.AppToolbar
import `in`.surajsau.jisho.android.ui.reference.jlpt.components.JlptRow
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun JlptResourceScreen(
    modifier: Modifier = Modifier,
    navigateToJlptLevel: (Int) -> Unit,
    navigateBack: () -> Unit = {},
) {

    Column(modifier = modifier) {

        AppToolbar(
            modifier = Modifier.fillMaxWidth(),
            navigateUpIcon = Icons.Default.ArrowBack,
            title = "JLPT",
            onNavigateUp = navigateBack
        )

        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(all = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            JlptRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navigateToJlptLevel(5) },
                title = "N5",
                description = "Understanding of some Basic Japanese"
            )

            JlptRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navigateToJlptLevel(4) },
                title = "N4",
                description = "Understanding of Basic Japanese"
            )

            JlptRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navigateToJlptLevel(3) },
                title = "N3",
                description = "Understanding of some Japanese used in everyday situations to some degre"
            )

            JlptRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navigateToJlptLevel(2) },
                title = "N2",
                description = "Understanding of some Japanese used in everyday situations and irregular circumstances to some degree"
            )

            JlptRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navigateToJlptLevel(1) },
                title = "N1",
                description = "Understanding of Japanese even in irregular circumstances"
            )
        }
    }
}