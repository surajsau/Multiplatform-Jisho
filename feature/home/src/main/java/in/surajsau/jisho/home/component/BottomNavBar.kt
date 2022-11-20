package `in`.surajsau.jisho.home.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import `in`.surajsau.jisho.home.model.HomeDestination

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    currentDestination: HomeDestination? = null,
    onNavItemTapped: (HomeDestination) -> Unit
) {
    NavigationBar(modifier = modifier) {
        HomeDestination.values().forEach { destination ->
            val selected = currentDestination == destination
            NavigationBarItem(
                label = { Text(text = destination.label) },
                icon = {
                    Icon(
                        painter = painterResource(
                            id = if (selected)
                                destination.selectedIcon
                            else destination.icon
                        ),
                        contentDescription = destination.label
                    )
                },
                onClick = { onNavItemTapped(destination) },
                selected = selected
            )
        }
    }
}