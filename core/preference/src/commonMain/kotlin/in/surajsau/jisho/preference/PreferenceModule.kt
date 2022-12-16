package `in`.surajsau.jisho.preference

import com.russhwolf.settings.ExperimentalSettingsApi
import org.koin.core.module.Module
import org.koin.dsl.module

@OptIn(ExperimentalSettingsApi::class)
public val PreferenceModule: Module = module {
    single { Preferences(settings = settings()) }
}