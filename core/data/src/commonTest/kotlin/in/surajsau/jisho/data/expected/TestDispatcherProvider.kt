package `in`.surajsau.jisho.data.expected

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler

@OptIn(ExperimentalCoroutinesApi::class)
class TestDispatcherProvider(
    private val scheduler: TestCoroutineScheduler
) : DispatcherProvider {

    override val io: CoroutineDispatcher
        get() = StandardTestDispatcher(scheduler)

    override val main: CoroutineDispatcher
        get() = StandardTestDispatcher(scheduler)
}
