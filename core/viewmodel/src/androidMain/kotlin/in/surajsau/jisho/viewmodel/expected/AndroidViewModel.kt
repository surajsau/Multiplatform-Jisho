package `in`.surajsau.jisho.viewmodel.expected

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

public actual abstract class BaseViewModel<S : UiState> : ViewModel() {
    public actual abstract val state: StateFlow<S>

    public actual val scope: CoroutineScope = viewModelScope
    actual override fun onCleared() {}
}