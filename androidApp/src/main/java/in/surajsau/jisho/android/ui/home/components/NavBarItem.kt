package `in`.surajsau.jisho.android.ui.home.components

import `in`.surajsau.jisho.android.neomorphic.NeomorphicShape
import `in`.surajsau.jisho.android.neomorphic.neomorph
import `in`.surajsau.jisho.android.ui.Navigation
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NavBarItem(
    modifier: Modifier = Modifier,
    item: Navigation.Home,
    isSelected: Boolean = false,
    onItemClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier
                .clickable { onItemClick() }
                .size(48.dp)
                .neomorph(
                    shape = NeomorphicShape.Oval,
                    isPressed = isSelected,
                    elevation = 6.dp,
                )
                .padding(12.dp),
            imageVector = if (isSelected) item.selectedIcon else item.icon,
            contentDescription = item.title
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = item.title,
            fontSize = 12.sp,
        )
    }
}