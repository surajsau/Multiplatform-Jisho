package `in`.surajsau.jisho.android

import android.app.Application
import `in`.surajsau.jisho.di.initKoin
import `in`.surajsau.jisho.expected.initiateLogger
import org.koin.android.ext.koin.androidContext

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@App)
        }
        initiateLogger()
    }
}
