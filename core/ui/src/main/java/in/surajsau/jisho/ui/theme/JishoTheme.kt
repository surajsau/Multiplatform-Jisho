package `in`.surajsau.jisho.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import `in`.surajsau.jisho.model.ThemePreference

@Composable
fun JishoTheme(
    themePreference: ThemePreference = ThemePreference.Default(false),
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    val colorScheme = when(themePreference) {
        is ThemePreference.Dynamic -> if (themePreference.dark) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        is ThemePreference.Default -> if (themePreference.dark) DarkColors else LightColors
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}