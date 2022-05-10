package `in`.surajsau.jisho.di

import `in`.surajsau.jisho.data.DatabaseModule
import `in`.surajsau.jisho.data.DispatcherModule
import `in`.surajsau.jisho.data.real.ConjugationRepositoryImpl
import `in`.surajsau.jisho.data.real.JlptRepositoryImpl
import `in`.surajsau.jisho.data.real.JmdictRepositoryImpl
import `in`.surajsau.jisho.data.real.KanjidicRepositoryImpl
import `in`.surajsau.jisho.data.real.NotesRepositoryImpl
import `in`.surajsau.jisho.data.real.SentenceRepositoryImpl
import `in`.surajsau.jisho.data.repository.ConjugationRepository
import `in`.surajsau.jisho.data.repository.JlptRepository
import `in`.surajsau.jisho.data.repository.JmdictRepository
import `in`.surajsau.jisho.data.repository.KanjidicRepository
import `in`.surajsau.jisho.data.repository.NotesRepository
import `in`.surajsau.jisho.data.repository.SentenceRepository
import `in`.surajsau.jisho.usecase.AddNoteForSentence
import `in`.surajsau.jisho.usecase.GetAllForJlptLevel
import `in`.surajsau.jisho.usecase.GetConjugations
import `in`.surajsau.jisho.usecase.GetEntry
import `in`.surajsau.jisho.usecase.GetFilteredKanjis
import `in`.surajsau.jisho.usecase.GetKanji
import `in`.surajsau.jisho.usecase.GetNote
import `in`.surajsau.jisho.usecase.GetNumberOfSentencesForWord
import `in`.surajsau.jisho.usecase.GetSentenceDetails
import `in`.surajsau.jisho.usecase.GetSentencesForWord
import `in`.surajsau.jisho.usecase.RemoveNote
import `in`.surajsau.jisho.usecase.SearchForKanji
import `in`.surajsau.jisho.usecase.SearchForReading
import `in`.surajsau.jisho.usecase.UpdateNotes
import `in`.surajsau.jisho.viewmodel.DetailsViewModel
import `in`.surajsau.jisho.viewmodel.JlptListViewModel
import `in`.surajsau.jisho.viewmodel.KanjiListViewModel
import `in`.surajsau.jisho.viewmodel.SearchViewModel
import `in`.surajsau.jisho.viewmodel.SentenceDetailViewModel
import `in`.surajsau.jisho.viewmodel.SentenceListViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val UsecaseModule = module {
    factory { AddNoteForSentence(get(), get()) }
    factory { RemoveNote(get()) }
    factory { SearchForKanji(get(), get()) }
    factory { SearchForReading(get(), get()) }
    factory { UpdateNotes(get()) }
    factory { GetNote(get()) }
    factory { GetFilteredKanjis(get()) }
    factory { GetEntry(get()) }
    factory { GetKanji(get()) }
    factory { GetSentencesForWord(get()) }
    factory { GetAllForJlptLevel(get(), get()) }
    factory { GetSentenceDetails(get(), get()) }
    factory { GetConjugations(get()) }
    factory { GetNumberOfSentencesForWord(get()) }
}

val ViewModelModule = module {
    factory { SearchViewModel() }
    factory { KanjiListViewModel() }
    factory { DetailsViewModel() }
    factory { SentenceDetailViewModel() }
    factory { JlptListViewModel() }
    factory { SentenceListViewModel() }
}

val RepositoryModule = module {
    factory<JmdictRepository> {
//        if (isDebug()) {
//            FakeJmdictRepository()
//        } else {
//            JmdictRepositoryImpl(get(), get())
//        }
        JmdictRepositoryImpl(get(), get())
    }
    factory<SentenceRepository> { SentenceRepositoryImpl(get(), get()) }
    factory<NotesRepository> { NotesRepositoryImpl(get(), get()) }
    factory<KanjidicRepository> { KanjidicRepositoryImpl(get(), get()) }
    factory<JlptRepository> { JlptRepositoryImpl(get(), get()) }
    factory<ConjugationRepository> { ConjugationRepositoryImpl(get(), get()) }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(
        DispatcherModule,
        DatabaseModule,
        RepositoryModule,
        UsecaseModule,
        ViewModelModule,
    )
}

fun initKoin() = initKoin {}
