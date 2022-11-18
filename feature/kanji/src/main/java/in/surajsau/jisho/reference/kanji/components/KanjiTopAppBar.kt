package `in`.surajsau.jisho.reference.kanji.components

import androidx.compose.material.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import `in`.surajsau.jisho.reference.kanji.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KanjiTopAppBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
    onBackClicked: () -> Unit,
) {
    LargeTopAppBar(
        modifier = modifier,
        title = { Text(text = "JLPT") },
        navigationIcon = {
            IconButton(onClick = onBackClicked) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Kanji Back"
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}