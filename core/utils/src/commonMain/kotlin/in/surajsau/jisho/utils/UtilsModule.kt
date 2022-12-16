package `in`.surajsau.jisho.utils

import org.koin.core.module.Module
import org.koin.dsl.module

public val UtilsModule: Module = module {
    factory { getDispatcherProvider() }
}