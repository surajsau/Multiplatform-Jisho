package `in`.surajsau.jisho.neumorphic

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

data class NeumorphicStyle(
    val lightColor: Color,
    val darkColor: Color,
    val elevation: Dp,
    val lightSource: LightSource,
)