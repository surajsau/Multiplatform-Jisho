package `in`.surajsau.jisho.android

import android.app.Application
import `in`.surajsau.jisho.app.AndroidDownloadManager
import `in`.surajsau.jisho.app.AppModule
import `in`.surajsau.jisho.initKoin
import org.koin.android.ext.koin.androidContext

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin(
            downloadManager = { AndroidDownloadManager(this) }
        ) {
            androidContext(this@App)
            modules(AppModule)
        }
    }
}
