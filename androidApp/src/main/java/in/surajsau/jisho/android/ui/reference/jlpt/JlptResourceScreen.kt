package `in`.surajsau.jisho.android.ui.reference.jlpt

import `in`.surajsau.jisho.android.ui.components.AppToolbar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun JlptResourceScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit = {},
) {

    Column(modifier = modifier) {

        AppToolbar(
            modifier = Modifier.fillMaxWidth(),
            navigateUpIcon = Icons.Default.ArrowBack,
            title = "JLPT",
            onNavigateUp = navigateBack
        )


    }
}