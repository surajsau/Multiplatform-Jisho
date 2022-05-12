package `in`.surajsau.jisho.android.ui.reference.jlpt

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.android.ui.components.AppToolbar
import `in`.surajsau.jisho.android.ui.reference.jlpt.components.JlptRow

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
                    .fillMaxWidth(),
                title = "N5",
                description = "Understanding of some Basic Japanese",
                onItemClicked = { navigateToJlptLevel(5) }
            )

            JlptRow(
                modifier = Modifier
                    .fillMaxWidth(),
                title = "N4",
                description = "Understanding of Basic Japanese",
                onItemClicked = { navigateToJlptLevel(4) }
            )

            JlptRow(
                modifier = Modifier
                    .fillMaxWidth(),
                title = "N3",
                description = "Understanding of some Japanese used in everyday situations to some degree",
                onItemClicked = { navigateToJlptLevel(3) }
            )

            JlptRow(
                modifier = Modifier
                    .fillMaxWidth(),
                title = "N2",
                description = "Understanding of some Japanese used in everyday situations and irregular circumstances to some degree",
                onItemClicked = { navigateToJlptLevel(2) }
            )

            JlptRow(
                modifier = Modifier
                    .fillMaxWidth(),
                title = "N1",
                description = "Understanding of Japanese even in irregular circumstances",
                onItemClicked = { navigateToJlptLevel(1) }
            )
        }
    }
}
