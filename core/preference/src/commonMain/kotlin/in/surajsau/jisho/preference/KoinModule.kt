package `in`.surajsau.jisho.preference

import org.koin.dsl.module

val PreferenceModule = module {
    single { Preferences(settings = settings()) }
}