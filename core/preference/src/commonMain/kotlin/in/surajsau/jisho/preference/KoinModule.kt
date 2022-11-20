package `in`.surajsau.jisho.preference

import com.russhwolf.settings.ExperimentalSettingsApi
import org.koin.dsl.module

@OptIn(ExperimentalSettingsApi::class)
val PreferenceModule = module {
    single { Preferences(settings = settings()) }
}