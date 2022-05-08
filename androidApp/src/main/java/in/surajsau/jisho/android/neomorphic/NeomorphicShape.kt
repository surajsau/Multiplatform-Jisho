package `in`.surajsau.jisho.android.neomorphic

import androidx.compose.ui.unit.Dp

sealed interface NeomorphicShape {
    object Oval : NeomorphicShape
    data class RoundedCorner(val corner: Dp) : NeomorphicShape
}