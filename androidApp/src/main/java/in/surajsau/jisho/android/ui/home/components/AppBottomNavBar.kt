package `in`.surajsau.jisho.android.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.android.ui.NavigationItem
import `in`.surajsau.jisho.android.ui.theme.LightColors

@Composable
fun AppBottomNavBar(
    modifier: Modifier = Modifier,
    onItemSelected: (NavigationItem.Home) -> Unit
) {
    val items = remember {
        listOf(
            NavigationItem.Home.Search,
            NavigationItem.Home.Favorite,
            NavigationItem.Home.Lists,
            NavigationItem.Home.Settings
        )
    }

    Row(
        modifier = modifier.padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        var currentSelection by remember { mutableStateOf<NavigationItem.Home>(NavigationItem.Home.Search) }

        items.forEach { item ->
            NavBarItem(
                item = item,
                isSelected = item == currentSelection
            ) {
                currentSelection = item
                onItemSelected(item)
            }
        }
    }
}

@Preview
@Composable
private fun previewAppBottomNavBar() {
    MaterialTheme(colors = LightColors) {
        Surface {
            Box(modifier = Modifier.fillMaxSize()) {
                AppBottomNavBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                ) {}
            }
        }
    }
}
