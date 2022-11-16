package `in`.surajsau.jisho.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun RowScope.JishoNavigationBarItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onClick: () -> Unit,
    @DrawableRes iconRes: Int,
    label: String,
) {
    NavigationBarItem(
        modifier = modifier,
        selected = selected,
        onClick = onClick,
        icon = {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = "$label icon",
            )
        },
        label = { Text(text = label) },
        enabled = true,
        alwaysShowLabel = true
    )
}