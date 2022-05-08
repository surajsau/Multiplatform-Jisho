package `in`.surajsau.jisho.android.neomorphic

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

data class NeomorphicStyle(
    val lightColor: Color,
    val darkColor: Color,
    val elevation: Dp,
    val lightSource: LightSource,
)