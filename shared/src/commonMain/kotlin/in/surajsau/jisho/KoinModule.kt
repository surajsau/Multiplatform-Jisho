package `in`.surajsau.jisho

import `in`.surajsau.jisho.viewmodel.ViewModelModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(
    appDeclaration: KoinAppDeclaration = {},
) = startKoin {
    appDeclaration()
    modules(
        ViewModelModule,
    )
}

fun initKoin() = initKoin{}
