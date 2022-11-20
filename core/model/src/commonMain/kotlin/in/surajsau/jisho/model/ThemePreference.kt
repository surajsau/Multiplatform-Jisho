package `in`.surajsau.jisho.model

sealed interface ThemePreference {
    data class Dynamic(val dark: Boolean): ThemePreference
    data class Default(val dark: Boolean): ThemePreference
}