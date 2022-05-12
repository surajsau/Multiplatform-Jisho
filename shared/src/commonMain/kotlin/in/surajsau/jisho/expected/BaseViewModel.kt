package `in`.surajsau.jisho.expected

import `in`.surajsau.jisho.viewmodel.UnidirectionalViewModel
import `in`.surajsau.jisho.viewmodel.VMEffect
import `in`.surajsau.jisho.viewmodel.VMIntent
import `in`.surajsau.jisho.viewmodel.VMState
import kotlinx.coroutines.CoroutineScope

expect abstract class BaseViewModel<S : VMState, I : VMIntent, E : VMEffect>() : UnidirectionalViewModel<S, I, E> {
    val scope: CoroutineScope
    protected open fun onCleared()
}
