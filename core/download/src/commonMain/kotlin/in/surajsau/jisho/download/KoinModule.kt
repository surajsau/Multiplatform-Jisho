package `in`.surajsau.jisho.download

import org.koin.dsl.module

val DownloadModule = module {
    single { downloadManager() }
}