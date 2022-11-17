package `in`.surajsau.jisho.app.model

import `in`.surajsau.jisho.model.ThemePreference
import `in`.surajsau.jisho.navigation.AppDestination
import `in`.surajsau.jisho.search.navigation.SearchDestination

data class AppState(
    val themePreference: ThemePreference = ThemePreference.Default(false),
    val startDestination: AppDestination = SearchDestination
)