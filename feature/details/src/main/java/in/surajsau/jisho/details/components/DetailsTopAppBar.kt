package `in`.surajsau.jisho.details.components

import androidx.compose.material.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import `in`.surajsau.jisho.details.R
import `in`.surajsau.jisho.viewmodel.DetailsViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun DetailsTopAppBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
    viewModel: DetailsViewModel = koinViewModel(),
    onBackClicked: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LargeTopAppBar(
        modifier = modifier,
        title = { Text(text = state.header.kanji) },
        navigationIcon = {
            IconButton(onClick = onBackClicked) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Details Back"
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}