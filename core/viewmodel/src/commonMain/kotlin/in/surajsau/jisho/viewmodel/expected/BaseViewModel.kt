package `in`.surajsau.jisho.viewmodel.expected

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

public interface State

public expect abstract class BaseViewModel<S : State>() {
    public abstract val state: StateFlow<S>

    public val scope: CoroutineScope
    protected open fun onCleared()
}