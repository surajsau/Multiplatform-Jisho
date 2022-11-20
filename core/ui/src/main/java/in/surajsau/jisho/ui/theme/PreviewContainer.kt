package `in`.surajsau.jisho.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import `in`.surajsau.jisho.model.ThemePreference

@Composable
fun PreviewContainer(
    content: @Composable () -> Unit
) {
    JishoTheme(
        themePreference = ThemePreference.Default(isSystemInDarkTheme()),
        content = content
    )
}