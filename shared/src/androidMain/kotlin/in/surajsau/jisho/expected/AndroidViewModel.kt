package `in`.surajsau.jisho.expected

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import `in`.surajsau.jisho.viewmodel.UnidirectionalViewModel
import `in`.surajsau.jisho.viewmodel.VMEffect
import `in`.surajsau.jisho.viewmodel.VMIntent
import `in`.surajsau.jisho.viewmodel.VMState

actual abstract class BaseViewModel<S : VMState, I : VMIntent, E : VMEffect> :
    ViewModel(), UnidirectionalViewModel<S, I, E> {
    actual val scope = viewModelScope
    actual override fun onCleared() {}
}