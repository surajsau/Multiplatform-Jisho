package `in`.surajsau.jisho.utils

import kotlinx.coroutines.CoroutineDispatcher

public expect fun getDispatcherProvider(): DispatcherProvider

public interface DispatcherProvider {
    public val io: CoroutineDispatcher
    public val main: CoroutineDispatcher
}