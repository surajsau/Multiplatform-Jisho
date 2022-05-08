package `in`.surajsau.jisho.expected

import `in`.surajsau.jisho.viewmodel.UnidirectionalViewModel
import `in`.surajsau.jisho.viewmodel.VMEffect
import `in`.surajsau.jisho.viewmodel.VMIntent
import `in`.surajsau.jisho.viewmodel.VMState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

actual abstract class BaseViewModel<S : VMState, I : VMIntent, E : VMEffect> : UnidirectionalViewModel<S, I, E> {

    private var hasCleared = false

    actual val scope: CoroutineScope by lazy {
        CloseableCoroutineScope(SupervisorJob() + Dispatchers.Main.immediate).also {
            if (hasCleared)
                it.close()
        }
    }

    protected actual open fun onCleared() {
        hasCleared = false
        (scope as? CloseableCoroutineScope)?.close()
    }
}