package `in`.surajsau.jisho.android.ui

import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import `in`.surajsau.jisho.navigation.HomeDestination
import `in`.surajsau.jisho.ui.component.NavBarItem

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    destinations: List<HomeDestination>,
    currentDestination: HomeDestination? = null,
    onNavItemTapped: (HomeDestination) -> Unit
) {
    NavigationBar(modifier = modifier) {
        destinations.forEach { destination ->
            NavBarItem(
                title = destination.title,
                selectedIcon = destination.selectedIcon,
                icon = destination.icon,
                isSelected = currentDestination == destination,
                onItemClick = { onNavItemTapped(destination) }
            )
        }
    }
}