package `in`.surajsau.jisho.android.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import `in`.surajsau.jisho.navigation.AppDestination
import `in`.surajsau.jisho.navigation.HomeDestination

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    destinations: List<HomeDestination>,
    currentDestination: AppDestination? = null,
    onNavItemTapped: (HomeDestination) -> Unit
) {
    NavigationBar(modifier = modifier) {
        destinations.forEach { destination ->
            val selected = currentDestination is HomeDestination
                && currentDestination == destination
            NavigationBarItem(
                label = { Text(text = destination.title) },
                icon = {
                    Icon(
                        painter = painterResource(
                            id = if (selected)
                                destination.selectedIcon
                            else destination.icon
                        ),
                        contentDescription = destination.title
                    )
                },
                onClick = { onNavItemTapped(destination) },
                selected = selected
            )
        }
    }
}