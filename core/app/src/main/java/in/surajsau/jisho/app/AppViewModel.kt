package `in`.surajsau.jisho.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import `in`.surajsau.jisho.app.model.AppState
import `in`.surajsau.jisho.model.ThemePreference
import `in`.surajsau.jisho.navigation.AppDestination
import `in`.surajsau.jisho.preference.Preferences
import `in`.surajsau.jisho.search.navigation.SearchDestination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AppViewModel constructor(
    private val preferences: Preferences
) : ViewModel() {

    private val _theme = MutableStateFlow<ThemePreference>(ThemePreference.Default(false))
    private val _currentDestination = MutableStateFlow<AppDestination?>(null)

    val state: StateFlow<AppState> = combine(_theme, _currentDestination) { values ->
        val theme = values[0] as ThemePreference
        val destination = values[1] as? AppDestination ?: SearchDestination

        return@combine AppState(
            themePreference = theme,
            startDestination = destination
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), AppState())

    init {
        viewModelScope.launch {
            preferences.theme
                .collect {
                    _theme.value = it
                }
        }
    }

    fun setDarkMode(value: Boolean) {
        viewModelScope.launch { preferences.setDarkMode(value) }
    }

    fun setDynamicTheme(value: Boolean) {
        viewModelScope.launch { preferences.setDynamicTheme(value) }
    }
}