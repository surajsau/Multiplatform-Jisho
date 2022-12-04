package `in`.surajsau.jisho.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import `in`.surajsau.jisho.model.Bucket
import `in`.surajsau.jisho.ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    bucket: Bucket?,
    scrollBehavior: TopAppBarScrollBehavior,
    onLikeClicked: () -> Unit,
    onBackClicked: () -> Unit,
) {
    val tagged = bucket != null

    LargeTopAppBar(
        modifier = modifier,
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onBackClicked) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Details Back"
                )
            }
        },
        actions = {
            IconButton(onClick = onLikeClicked) {
                Icon(
                    painter = painterResource(
                        id = if (tagged) R.drawable.ic_tag_filled
                        else R.drawable.ic_tag
                    ),
                    contentDescription = "Details Back"
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}