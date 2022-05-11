package `in`.surajsau.jisho.expected

import `in`.surajsau.jisho.data.BuildConfig
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

fun initiateLogger() {
    if (BuildConfig.DEBUG)
        Napier.base(DebugAntilog())
}