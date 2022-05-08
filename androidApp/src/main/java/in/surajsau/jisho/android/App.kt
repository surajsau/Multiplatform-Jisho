package `in`.surajsau.jisho.android

import android.app.Application
import `in`.surajsau.jisho.di.initKoin
import org.koin.android.ext.koin.androidContext

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@App)
        }
    }
}