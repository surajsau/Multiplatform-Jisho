package `in`.surajsau.jisho.sentence.list.components

import androidx.compose.material.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import `in`.surajsau.jisho.sentence.list.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SentenceListTopBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
    onBackClicked: () -> Unit,
) {
    LargeTopAppBar(
        modifier = modifier,
        title = { Text(text = "Sentences") },
        navigationIcon = {
            IconButton(onClick = onBackClicked) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Sentence List Back"
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}