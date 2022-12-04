package `in`.surajsau.jisho.android

import android.app.Application
import `in`.surajsau.jisho.app.AppModule
import `in`.surajsau.jisho.download.DownloadModule
import `in`.surajsau.jisho.initKoin
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@App)
            modules(AppModule, DownloadModule)
        }
    }
}
