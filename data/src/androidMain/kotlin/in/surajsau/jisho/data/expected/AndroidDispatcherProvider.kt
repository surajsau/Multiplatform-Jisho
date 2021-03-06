package `in`.surajsau.jisho.data.expected

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual fun getDispatcherProvider(): DispatcherProvider = AndroidDispatcherProvider()

class AndroidDispatcherProvider : DispatcherProvider {

    override val io: CoroutineDispatcher
        get() = Dispatchers.IO

    override val main: CoroutineDispatcher
        get() = Dispatchers.Main
}