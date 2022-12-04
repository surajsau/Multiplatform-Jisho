package `in`.surajsau.jisho.tagged

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.tagged.components.AddTagCard
import `in`.surajsau.jisho.tagged.components.TagRow
import `in`.surajsau.jisho.tagged.model.TagsScreenModel
import `in`.surajsau.jisho.ui.R

@Composable
fun TagsScreen(
    modifier: Modifier = Modifier,
    screenModel: TagsScreenModel,
    onItemClicked: (Long) -> Unit
) {
    val uiState = screenModel.uiState

    if (screenModel.showAddTagDialog) {

    }

    Column(
        modifier = modifier
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TagRow(
            icon = {
               Icon(
                   painter = painterResource(id = R.drawable.ic_like),
                   contentDescription = null,
                   tint = Color.White
               )
            },
            color = MaterialTheme.colorScheme.secondary,
            title = "Favorites",
            description = "123 items",
            onItemClicked = {}
        )

        uiState.tags.forEach { tag ->
            TagRow(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_tag),
                        contentDescription = null,
                        tint = Color(color = tag.color)
                    )
                },
                color = MaterialTheme.colorScheme.secondary,
                title = tag.bucket.name,
                description = "${tag.count} items",
                onItemClicked = { onItemClicked(tag.bucket.id) }
            )
        }

        AddTagCard(
            onClicked = screenModel::onAddTagCardClicked
        )
    }
}