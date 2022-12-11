package `in`.surajsau.jisho.viewmodel.expected

import `in`.surajsau.jisho.viewmodel.CloseableCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.StateFlow

public actual abstract class BaseViewModel<S : UiState> {
    public actual abstract val state: StateFlow<S>

    private var hasCleared = false

    public actual val scope: CoroutineScope by lazy {
        CloseableCoroutineScope(SupervisorJob() + Dispatchers.Main.immediate).also {
            if (hasCleared) {
                it.close()
            }
        }
    }

    protected actual open fun onCleared() {
        hasCleared = false
        (scope as? CloseableCoroutineScope)?.close()
    }
}
