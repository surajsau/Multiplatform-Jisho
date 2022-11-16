package `in`.surajsau.jisho.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import `in`.surajsau.jisho.neumorphic.NeumorphDarkColors
import `in`.surajsau.jisho.neumorphic.NeumorphLightColors

@Composable
fun AppTheme(
    isNeumorphic: Boolean = false,
    isDarkMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    val colorScheme = when {
        isNeumorphic && !isDarkMode -> NeumorphLightColors
        isNeumorphic && isDarkMode -> NeumorphDarkColors
        isDarkMode -> dynamicDarkColorScheme(context)
        else -> dynamicLightColorScheme(context)
    }

    MaterialTheme(
        colorScheme = colorScheme
    ) {
        Surface {
            content()
        }
    }
}