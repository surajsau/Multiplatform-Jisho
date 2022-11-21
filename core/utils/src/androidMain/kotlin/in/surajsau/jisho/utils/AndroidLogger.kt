package `in`.surajsau.jisho.utils

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

public fun initiateLogger() {
    if (BuildConfig.DEBUG) {
        Napier.base(DebugAntilog())
    }
}
