package `in`.surajsau.jisho.preference

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.datastore.DataStoreSettings
import org.koin.android.ext.koin.androidApplication
import org.koin.core.scope.Scope

private val Context.dataStore by preferencesDataStore(name = "settings")

@OptIn(ExperimentalSettingsImplementation::class, ExperimentalSettingsApi::class)
actual fun Scope.settings(): FlowSettings {
    return DataStoreSettings(androidApplication().dataStore)
}