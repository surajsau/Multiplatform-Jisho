package `in`.surajsau.jisho.data

import `in`.surajsau.jisho.data.db.Jisho
import `in`.surajsau.jisho.data.expected.createDriver
import `in`.surajsau.jisho.data.expected.getDispatcherProvider
import org.koin.dsl.module

val DispatcherModule = module {
    factory { getDispatcherProvider() }
}

val DatabaseModule = module {
    factory { Jisho(createDriver("jmdict.db")) }
}