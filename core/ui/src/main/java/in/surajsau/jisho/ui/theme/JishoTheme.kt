package `in`.surajsau.jisho.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import `in`.surajsau.jisho.model.ThemePreference

@Composable
fun JishoTheme(
    themePreference: ThemePreference = ThemePreference.DynamicSystemDefault,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    val isDynamicTheme = when {
        Build.VERSION.SDK_INT < Build.VERSION_CODES.S -> false

        (themePreference is ThemePreference.Dynamic) or
            (themePreference is ThemePreference.DynamicSystemDefault) -> true

        else -> false
    }

    val isDarkTheme = when(themePreference) {
        is ThemePreference.Dynamic -> themePreference.dark
        is ThemePreference.Default -> themePreference.dark

        is ThemePreference.DynamicSystemDefault,
        is ThemePreference.SystemDefault -> isSystemInDarkTheme()
    }


    val colorScheme = when {
        isDynamicTheme && isDarkTheme -> dynamicDarkColorScheme(context)
        isDynamicTheme -> dynamicLightColorScheme(context)
        isDarkTheme -> DarkColors
        else -> LightColors
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}