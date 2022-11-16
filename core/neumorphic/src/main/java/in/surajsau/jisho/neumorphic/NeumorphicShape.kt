package `in`.surajsau.jisho.neumorphic

import androidx.compose.ui.unit.Dp

sealed interface NeumorphicShape {
    object Oval : NeumorphicShape
    data class RoundedCorner(val corner: Dp) : NeumorphicShape
}