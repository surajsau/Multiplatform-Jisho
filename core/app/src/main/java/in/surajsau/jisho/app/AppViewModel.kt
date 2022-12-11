package `in`.surajsau.jisho.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import `in`.surajsau.jisho.download.DownloadManager
import `in`.surajsau.jisho.feature.download.navigation.DownloadNavGraph
import `in`.surajsau.jisho.home.navigation.HomeNavGraph
import `in`.surajsau.jisho.model.ThemePreference
import `in`.surajsau.jisho.preference.Preferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AppViewModel constructor(
    private val preferences: Preferences,
    downloadManager: DownloadManager,
) : ViewModel() {

    private val _theme = MutableStateFlow<ThemePreference>(ThemePreference.Default(false))

    private val _startDestination = MutableStateFlow<String?>(null)

    val state: StateFlow<State> = combine(
        _theme,
        _startDestination
    ) { values ->
        val themePreference = values[0] as ThemePreference
        val startDestination = values[1] as String

        State(
            themePreference = themePreference,
            startDestination = startDestination
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), State())

    init {
        viewModelScope.launch {
            preferences.theme
                .collect {
                    _theme.value = it
                }
        }

        val databaseExists = downloadManager.checkIfDatabaseExists()
        _startDestination.value = if (databaseExists) HomeNavGraph.homeRoute() else DownloadNavGraph.downloadRoute()
    }

    fun setDarkMode(value: Boolean) {
        viewModelScope.launch { preferences.setDarkMode(value) }
    }

    fun setDynamicTheme(value: Boolean) {
        viewModelScope.launch { preferences.setDynamicTheme(value) }
    }

    data class State(
        val themePreference: ThemePreference = ThemePreference.Default(false),
        val startDestination: String? = null
    ) {
        val hideSplash: Boolean
            get() = startDestination != null
    }
}