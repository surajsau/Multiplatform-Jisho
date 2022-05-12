package `in`.surajsau.jisho.viewmodel

import `in`.surajsau.jisho.expected.BaseViewModel
import `in`.surajsau.jisho.model.SearchResult
import `in`.surajsau.jisho.usecase.SearchForKanji
import `in`.surajsau.jisho.usecase.SearchForReading
import `in`.surajsau.jisho.utils.isKanji
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class SearchViewModel : BaseViewModel<SearchViewModel.State, SearchViewModel.Intent, SearchViewModel.Effect>(), KoinComponent {

    private val searchForKanji: SearchForKanji = get()
    private val searchForReading: SearchForReading = get()

    private val _searchTerm = MutableStateFlow("")

    private val _state = MutableStateFlow<State>(State.None)
    private val _effectChannel = Channel<Effect>()

    init {
        scope.launch {
            _searchTerm
                .filterNot { it.isEmpty() }
                .debounce(300)
                .map { searchTerm ->
                    val results = if (searchTerm.any { it.isKanji }) {
                        searchForKanji(text = searchTerm)
                    } else {
                        searchForReading(text = searchTerm)
                    }
                    if (results.isEmpty()) {
                        State.EmptyResult
                    } else {
                        State.Results(value = results)
                    }
                }
                .collect { _state.tryEmit(it) }
        }
    }

    override val state: StateFlow<State>
        get() = _state

    override fun onIntent(intent: Intent) {
        when (intent) {
            is Intent.SearchTextChanged -> {
                _searchTerm.tryEmit(intent.text)
            }
        }
    }

    override val effect: Flow<Effect>
        get() = _effectChannel.receiveAsFlow()

    sealed interface State : VMState {
        data class Results(val value: List<SearchResult>) : State

        object EmptyResult : State
        object None : State
    }
    sealed interface Intent : VMIntent {
        data class SearchTextChanged(val text: String) : Intent
    }

    sealed interface Effect : VMEffect
}
