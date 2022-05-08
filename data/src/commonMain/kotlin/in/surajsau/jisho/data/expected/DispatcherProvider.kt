package `in`.surajsau.jisho.data.expected

import kotlinx.coroutines.CoroutineDispatcher

expect fun getDispatcherProvider(): DispatcherProvider

interface DispatcherProvider {
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
}