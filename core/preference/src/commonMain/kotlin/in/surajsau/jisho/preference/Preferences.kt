package `in`.surajsau.jisho.preference

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.FlowSettings
import `in`.surajsau.jisho.model.ThemePreference
import kotlinx.coroutines.flow.combine
import org.koin.core.scope.Scope

expect fun Scope.settings(): FlowSettings

@OptIn(ExperimentalSettingsApi::class)
class Preferences (private val settings: FlowSettings) {

    private val _dynamic = settings.getBooleanFlow(KeyDynamic, false)
    private val _dark = settings.getBooleanFlow(KeyDarkMode, false)

    val theme = combine(_dynamic, _dark) { values ->
        val dynamic = values[0]
        val dark = values[1]

        return@combine when {
            dynamic -> ThemePreference.Dynamic(dark)
            else -> ThemePreference.Default(dark)
        }
    }

    suspend fun setDynamicTheme(value: Boolean) {
        settings.putBoolean(KeyDynamic, value)
    }

    suspend fun setDarkMode(value: Boolean) {
        settings.putBoolean(KeyDarkMode, value)
    }
}