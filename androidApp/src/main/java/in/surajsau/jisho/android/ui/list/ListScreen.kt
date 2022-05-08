package `in`.surajsau.jisho.android.ui.list

import `in`.surajsau.jisho.android.ui.components.AppToolbar
import `in`.surajsau.jisho.model.KanjiQuery
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    navigateToKanjiList: (KanjiQuery) -> Unit,
) {
    Column(modifier = modifier) {
        AppToolbar(
            modifier = Modifier.fillMaxWidth(),
            navigateUpIcon = { Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "") }

        )
    }
}