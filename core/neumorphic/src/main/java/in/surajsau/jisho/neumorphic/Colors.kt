package `in`.surajsau.jisho.neumorphic

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val NeumorphLightColors = lightColorScheme(
    primary = NeumorphicColors.Light.Background,
    onPrimary = NeumorphicColors.Light.TextColor,
    surface = NeumorphicColors.Light.Background,
    onSurface = NeumorphicColors.Light.TextColor,
    background = NeumorphicColors.Light.Background,
    onBackground = NeumorphicColors.Light.TextColor,
    error = Color(0xFFB00020),
    onError = Color.White,
    secondary = NeumorphicColors.Dark.DarkShadow,
    onSecondary = NeumorphicColors.Dark.TextColor,
)

val NeumorphDarkColors = darkColorScheme(
    primary = NeumorphicColors.Dark.Background,
    onPrimary = NeumorphicColors.Dark.TextColor,
    surface = NeumorphicColors.Dark.Background,
    onSurface = NeumorphicColors.Dark.TextColor,
    background = NeumorphicColors.Dark.Background,
    onBackground = NeumorphicColors.Dark.TextColor,
    error = Color(0xFFB00020),
    onError = Color.White,
    secondary = NeumorphicColors.Light.DarkShadow,
    onSecondary = NeumorphicColors.Light.TextColor,
)

internal val ColorScheme.lightShadow: Color
    @Composable get() = if (isSystemInDarkTheme()) NeumorphicColors.Light.LightShadow else NeumorphicColors.Dark.LightShadow

internal val ColorScheme.darkShadow: Color
    @Composable get() = if (isSystemInDarkTheme()) NeumorphicColors.Light.DarkShadow else NeumorphicColors.Dark.DarkShadow

internal val ColorScheme.neumorphicBackground: Color
    @Composable get() = if (isSystemInDarkTheme()) NeumorphicColors.Light.Background else NeumorphicColors.Dark.Background

internal object NeumorphicColors {

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