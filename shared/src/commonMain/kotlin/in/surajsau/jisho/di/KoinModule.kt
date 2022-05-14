package `in`.surajsau.jisho.di

import `in`.surajsau.jisho.data.DatabaseModule
import `in`.surajsau.jisho.data.DispatcherModule
import `in`.surajsau.jisho.data.expected.isDebug
import `in`.surajsau.jisho.data.real.BucketRepositoryImpl
import `in`.surajsau.jisho.data.real.ConjugationRepositoryImpl
import `in`.surajsau.jisho.data.real.JmdictRepositoryImpl
import `in`.surajsau.jisho.data.real.KanjidicRepositoryImpl
import `in`.surajsau.jisho.data.real.NotesRepositoryImpl
import `in`.surajsau.jisho.data.real.SentenceRepositoryImpl
import `in`.surajsau.jisho.data.repository.BucketRepository
import `in`.surajsau.jisho.data.repository.ConjugationRepository
import `in`.surajsau.jisho.data.repository.JmdictRepository
import `in`.surajsau.jisho.data.repository.KanjidicRepository
import `in`.surajsau.jisho.data.repository.NotesRepository
import `in`.surajsau.jisho.data.repository.SentenceRepository
import `in`.surajsau.jisho.fake.FakeBucketRepository
import `in`.surajsau.jisho.usecase.AddNoteForSentence
import `in`.surajsau.jisho.usecase.AddToBucket
import `in`.surajsau.jisho.usecase.CreateNewBucket
import `in`.surajsau.jisho.usecase.GetAllBuckets
import `in`.surajsau.jisho.usecase.GetAllForJlptLevel
import `in`.surajsau.jisho.usecase.GetConjugations
import `in`.surajsau.jisho.usecase.GetEntry
import `in`.surajsau.jisho.usecase.GetFilteredKanjis
import `in`.surajsau.jisho.usecase.GetKanji
import `in`.surajsau.jisho.usecase.GetNote
import `in`.surajsau.jisho.usecase.GetNumberOfSentencesForWord
import `in`.surajsau.jisho.usecase.GetSentenceDetails
import `in`.surajsau.jisho.usecase.GetSentencesForWord
import `in`.surajsau.jisho.usecase.RemoveBucket
import `in`.surajsau.jisho.usecase.RemoveFromBucket
import `in`.surajsau.jisho.usecase.RemoveNote
import `in`.surajsau.jisho.usecase.RenameBucket
import `in`.surajsau.jisho.usecase.SearchForKanji
import `in`.surajsau.jisho.usecase.SearchForReading
import `in`.surajsau.jisho.usecase.UpdateNotes
import `in`.surajsau.jisho.viewmodel.BucketsListViewModel
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
    // search
    factory { SearchForKanji(get(), get()) }
    factory { SearchForReading(get(), get()) }

    // sentence & notes
    factory { AddNoteForSentence(get(), get()) }
    factory { RemoveNote(get()) }
    factory { UpdateNotes(get()) }
    factory { GetNote(get()) }

    // bucket
    factory { AddToBucket(get()) }
    factory { CreateNewBucket(get()) }
    factory { GetAllBuckets(get()) }
    factory { RemoveBucket(get()) }
    factory { RemoveFromBucket(get()) }
    factory { RenameBucket(get()) }

    // references
    factory { GetFilteredKanjis(get()) }
    factory { GetAllForJlptLevel(get(), get()) }

    // entry details
    factory { GetEntry(get(), get(), get()) }
    factory { GetSentencesForWord(get()) }
    factory { GetSentenceDetails(get(), get()) }
    factory { GetConjugations(get()) }
    factory { GetNumberOfSentencesForWord(get()) }

    // kanji details
    factory { GetKanji(get()) }
}

val ViewModelModule = module {
    factory { SearchViewModel() }
    factory { KanjiListViewModel() }
    factory { DetailsViewModel() }
    factory { SentenceDetailViewModel() }
    factory { JlptListViewModel() }
    factory { SentenceListViewModel() }
    factory { BucketsListViewModel() }
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
    factory<BucketRepository> {
        if (isDebug()) {
            FakeBucketRepository()
        } else {
            BucketRepositoryImpl(get(), get())
        }
    }
    factory<SentenceRepository> { SentenceRepositoryImpl(get(), get()) }
    factory<NotesRepository> { NotesRepositoryImpl(get(), get()) }
    factory<KanjidicRepository> { KanjidicRepositoryImpl(get(), get()) }
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
