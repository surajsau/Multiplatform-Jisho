package `in`.surajsau.jisho.viewmodel

import `in`.surajsau.jisho.data.DataModule
import org.koin.core.module.Module
import org.koin.dsl.module

public val ViewModelModule: Module = module {
    includes(DataModule)
    single { SearchViewModel() }
    single { KanjiListViewModel() }
    single { DetailsViewModel() }
    single { SentenceDetailViewModel() }
    single { JlptListViewModel() }
    single { SentenceListViewModel() }
}