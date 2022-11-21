package `in`.surajsau.jisho

import `in`.surajsau.jisho.viewmodel.DetailsViewModel
import `in`.surajsau.jisho.viewmodel.JlptListViewModel
import `in`.surajsau.jisho.viewmodel.KanjiListViewModel
import `in`.surajsau.jisho.viewmodel.SearchViewModel
import `in`.surajsau.jisho.viewmodel.SentenceDetailViewModel
import `in`.surajsau.jisho.viewmodel.SentenceListViewModel
import `in`.surajsau.jisho.viewmodel.ViewModelModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(
        ViewModelModule,
    )
}

fun initKoin() = initKoin {}
