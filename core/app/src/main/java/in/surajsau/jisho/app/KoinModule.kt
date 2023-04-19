package `in`.surajsau.jisho.app

import `in`.surajsau.jisho.download.DownloadManager
import `in`.surajsau.jisho.preference.PreferenceModule
import `in`.surajsau.jisho.viewmodel.DownloadViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val AppModule = module {
    includes(PreferenceModule)
    viewModelOf(::AppViewModel)
    single<DownloadManager> {
        AndroidDownloadManager(
            context = androidContext(),
            ioScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
        )
    }
    single { DownloadViewModel(downloadManager = get()) }
}