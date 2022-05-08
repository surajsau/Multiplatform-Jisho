package `in`.surajsau.jisho.android.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val LightColors = lightColors(
    primary = AppColors.Light.Background,
    onPrimary = AppColors.Light.TextColor,
    surface = AppColors.Light.Background,
    onSurface = AppColors.Light.TextColor,
    background = AppColors.Light.Background,
    onBackground = AppColors.Light.TextColor,
    error = Color(0xFFB00020),
    onError = Color.White,
    secondary = AppColors.Dark.DarkShadow,
    onSecondary = AppColors.Dark.TextColor,
    primaryVariant = AppColors.Light.DarkShadow,
    secondaryVariant = AppColors.Dark.DarkShadow,
)

val DarkColors = darkColors(
    primary = AppColors.Dark.Background,
    onPrimary = AppColors.Dark.TextColor,
    surface = AppColors.Dark.Background,
    onSurface = AppColors.Dark.TextColor,
    background = AppColors.Dark.Background,
    onBackground = AppColors.Dark.TextColor,
    error = Color(0xFFB00020),
    onError = Color.White,
    secondary = AppColors.Light.DarkShadow,
    onSecondary = AppColors.Light.TextColor,
    primaryVariant = AppColors.Dark.DarkShadow,
    secondaryVariant = AppColors.Light.DarkShadow,
)

val Colors.lightShadow: Color
    get() = if (this.isLight) AppColors.Light.LightShadow else AppColors.Dark.LightShadow

val Colors.darkShadow: Color
    get() = if (this.isLight) AppColors.Light.DarkShadow else AppColors.Dark.DarkShadow

val Colors.neomorphicBackground: Color
    get() = if (this.isLight) AppColors.Light.Background else AppColors.Dark.Background

object AppColors {

    object Light {
        val Background = Color(0xFFECF0F3)
        val LightShadow = Color(0xFFFFFFFF)
        val DarkShadow = Color(0xFFD1D9E6)
        val TextColor = Color.Black
    }

    object Dark {
        val Background = Color(0xFF303234)
        val LightShadow = Color(0x66494949)
        val DarkShadow = Color(0x66000000)
        val TextColor = Color.White
    }
}