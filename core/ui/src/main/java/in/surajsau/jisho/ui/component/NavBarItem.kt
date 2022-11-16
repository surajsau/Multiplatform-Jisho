package `in`.surajsau.jisho.ui.component

import androidx.annotation.DrawableRes
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.surajsau.jisho.neumorphic.NeumorphicShape
import `in`.surajsau.jisho.neumorphic.clickableNeomorph

@Composable
fun NavBarItem(
    modifier: Modifier = Modifier,
    title: String,
    @DrawableRes selectedIcon: Int,
    @DrawableRes icon: Int,
    isSelected: Boolean = false,
    onItemClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier
                .size(48.dp)
                .clickableNeomorph(
                    shape = NeumorphicShape.Oval,
                    isPressed = isSelected,
                    elevation = 6.dp,
                ) { onItemClick() }
                .padding(12.dp),
            painter = painterResource(id = if (isSelected) selectedIcon else icon),
            contentDescription = title
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = title,
            fontSize = 12.sp,
        )
    }
}
