package `in`.surajsau.jisho.viewmodel

import `in`.surajsau.jisho.data.SearchForKanji
import `in`.surajsau.jisho.data.SearchForReading
import `in`.surajsau.jisho.model.SearchResult
import `in`.surajsau.jisho.utils.isKanji
import `in`.surajsau.jisho.viewmodel.expected.BaseViewModel
import `in`.surajsau.jisho.viewmodel.expected.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

public class SearchViewModel : BaseViewModel<SearchUiState>(), KoinComponent {

    private val searchForKanji: SearchForKanji = get()
    private val searchForReading: SearchForReading = get()

    private val _searchTerm = MutableStateFlow("")

    override val state: StateFlow<SearchUiState> = _searchTerm
        .filterNot { it.isEmpty() }
        .debounce(300)
        .map { searchTerm ->
            val results = when {
                searchTerm.any { it.isKanji } -> searchForKanji(text = searchTerm)
                else -> searchForReading(text = searchTerm)
            }
            SearchUiState(results = results)
        }
        .stateIn(scope, SharingStarted.WhileSubscribed(), SearchUiState())

    public fun onSearchTextChanged(text: String) {
        _searchTerm.value = text
    }
}

public data class SearchUiState(
    val results: List<SearchResult> = emptyList()
): UiState
