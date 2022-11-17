package `in`.surajsau.jisho.android

import android.app.Application
import `in`.surajsau.jisho.app.AppModule
import `in`.surajsau.jisho.app.AppViewModel
import `in`.surajsau.jisho.di.initKoin
import `in`.surajsau.jisho.expected.initiateLogger
import `in`.surajsau.jisho.preference.PreferenceModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@App)
            modules(AppModule)
        }
        initiateLogger()
    }
}
