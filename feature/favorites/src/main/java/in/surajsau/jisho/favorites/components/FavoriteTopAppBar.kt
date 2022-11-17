package `in`.surajsau.jisho.favorites.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteTopAppBar(
    modifier: Modifier = Modifier,
) {
    LargeTopAppBar(
        modifier = modifier,
        title = { Text(text = "Favorites") },
        navigationIcon = {},
        scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    )
}