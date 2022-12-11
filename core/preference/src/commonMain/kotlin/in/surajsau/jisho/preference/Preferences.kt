package `in`.surajsau.jisho.preference

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.FlowSettings
import `in`.surajsau.jisho.model.ThemePreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import org.koin.core.scope.Scope

@OptIn(ExperimentalSettingsApi::class)
public expect fun Scope.settings(): FlowSettings

@OptIn(ExperimentalSettingsApi::class)
public class Preferences (private val settings: FlowSettings) {

    private val _dynamic = settings.getBooleanFlow(KeyDynamic, false)
    private val _dark = settings.getBooleanFlow(KeyDarkMode, false)

    public val theme: Flow<ThemePreference> = combine(_dynamic, _dark) { values ->
        val dynamic = values[0]
        val dark = values[1]

        return@combine when {
            dynamic -> ThemePreference.Dynamic(dark)
            else -> ThemePreference.Default(dark)
        }
    }

    public suspend fun setDynamicTheme(value: Boolean) {
        settings.putBoolean(KeyDynamic, value)
    }

    public suspend fun setDarkMode(value: Boolean) {
        settings.putBoolean(KeyDarkMode, value)
    }
}