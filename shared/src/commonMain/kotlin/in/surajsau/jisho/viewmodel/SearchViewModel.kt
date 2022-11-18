package `in`.surajsau.jisho.viewmodel

import `in`.surajsau.jisho.expected.BaseViewModel
import `in`.surajsau.jisho.model.SearchResult
import `in`.surajsau.jisho.usecase.SearchForKanji
import `in`.surajsau.jisho.usecase.SearchForReading
import `in`.surajsau.jisho.utils.isKanji
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class SearchViewModel : BaseViewModel<SearchViewModel.State, SearchViewModel.Intent, SearchViewModel.Effect>(), KoinComponent {

    private val searchForKanji: SearchForKanji = get()
    private val searchForReading: SearchForReading = get()

    private val _searchTerm = MutableStateFlow("")

    private val _effectChannel = Channel<Effect>()

    override val state: StateFlow<State> = _searchTerm
        .filterNot { it.isEmpty() }
        .debounce(300)
        .map { searchTerm ->
            val results = when {
                searchTerm.any { it.isKanji } -> searchForKanji(text = searchTerm)
                else -> searchForReading(text = searchTerm)
            }
            State(results = results)
        }
        .stateIn(scope, SharingStarted.WhileSubscribed(), State())

    override fun onIntent(intent: Intent) {
        when (intent) {
            is Intent.SearchTextChanged -> {
                _searchTerm.value = intent.text
            }
        }
    }

    override val effect: Flow<Effect>
        get() = _effectChannel.receiveAsFlow()

    data class State(
        val results: List<SearchResult> = emptyList()
    ): VMState

    sealed interface Intent : VMIntent {
        data class SearchTextChanged(val text: String) : Intent
    }

    sealed interface Effect : VMEffect
}
