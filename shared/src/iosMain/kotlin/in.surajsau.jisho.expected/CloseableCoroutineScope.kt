package `in`.surajsau.jisho.expected

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

class CloseableCoroutineScope(private val context: CoroutineContext) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = context

    fun close() {
        context.cancel()
    }
}