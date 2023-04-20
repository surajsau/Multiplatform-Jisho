package `in`.surajsau.jisho.viewmodel

import `in`.surajsau.jisho.data.DataModule
import org.koin.core.module.Module
import org.koin.dsl.module

public val ViewModelModule: Module = module {
    includes(DataModule)
    single { SearchViewModel() }
    factory { KanjiListViewModel() }
    factory { DetailsViewModel() }
    factory { SentenceDetailViewModel() }
    factory { SentenceListViewModel() }
    factory { JlptListViewModel() }
}