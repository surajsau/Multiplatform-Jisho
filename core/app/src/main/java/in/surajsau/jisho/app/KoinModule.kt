package `in`.surajsau.jisho.app

import `in`.surajsau.jisho.preference.PreferenceModule
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val AppModule = module {
    includes(PreferenceModule)
    viewModelOf(::AppViewModel)
}