package `in`.surajsau.jisho

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

fun debugLog() {
    Napier.base(DebugAntilog())
}