package `in`.surajsau.jisho

import `in`.surajsau.jisho.download.DownloadManager
import `in`.surajsau.jisho.viewmodel.ViewModelModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(
    downloadManager: () -> DownloadManager,
    appDeclaration: KoinAppDeclaration = {},
) = startKoin {
    appDeclaration()
    modules(
        ViewModelModule,
    )
    module {
        single { downloadManager() }
    }
}

fun initKoin(downloadManager: DownloadManager) = initKoin(downloadManager = { downloadManager }){}
