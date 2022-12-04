package `in`.surajsau.jisho.data

import `in`.surajsau.jisho.data.db.Jisho
import `in`.surajsau.jisho.data.expected.createDriver
import `in`.surajsau.jisho.data.expected.getDispatcherProvider
import org.koin.core.module.Module
import org.koin.dsl.module

public val DataModule: Module = module {
    factory { getDispatcherProvider() }

    factory { AddNoteForSentence(get(), get()) }
    factory { GetAllForJlptLevel(get(), get()) }
    factory { GetConjugations(get(), get()) }
    factory { GetEntry(get(), get()) }
    factory { GetFilteredKanjis(get(), get()) }
    factory { GetKanji(get(), get()) }
    factory { GetNote(get(), get()) }
    factory { GetBucket(get(), get()) }
    factory { GetNumberOfSentencesForWord(get(), get()) }
    factory { GetSentenceDetails(get(), get(), get()) }
    factory { GetSentencesForWord(get(), get()) }
    factory { RemoveNote(get(), get()) }
    factory { SearchForKanji(get(), get(), get()) }
    factory { SearchForReading(get(), get()) }
    factory { UpdateNotes(get(), get()) }

    factory { Jisho(createDriver("jmdict.db")) }
}