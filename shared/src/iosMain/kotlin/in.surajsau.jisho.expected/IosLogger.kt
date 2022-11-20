package `in`.surajsau.jisho.expected

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

fun initiateLogger() {
    if (Platform.isDebugBinary) {
        Napier.base(DebugAntilog())
    }
}