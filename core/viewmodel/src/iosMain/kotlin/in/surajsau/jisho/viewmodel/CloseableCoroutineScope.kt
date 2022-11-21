package `in`.surajsau.jisho.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

public class CloseableCoroutineScope(private val context: CoroutineContext) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = context

    public fun close() {
        context.cancel()
    }
}