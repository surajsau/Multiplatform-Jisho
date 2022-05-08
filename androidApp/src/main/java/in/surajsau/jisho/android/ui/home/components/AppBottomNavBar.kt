package `in`.surajsau.jisho.android.ui.home.components

import `in`.surajsau.jisho.android.ui.Navigation
import `in`.surajsau.jisho.android.ui.theme.LightColors
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

@Composable
fun AppBottomNavBar(
    modifier: Modifier = Modifier,
    onItemSelected: (Navigation.Home) -> Unit
) {

    val items = remember {
        listOf(
            Navigation.Home.Search,
            Navigation.Home.Favorite,
            Navigation.Home.Lists,
            Navigation.Home.Settings
        )
    }

    Row(
        modifier = modifier.padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        var currentSelection by remember { mutableStateOf<Navigation.Home>(Navigation.Home.Search) }

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
