package `in`.surajsau.jisho.viewmodel

import `in`.surajsau.jisho.data.SearchForKanji
import `in`.surajsau.jisho.data.SearchForReading
import `in`.surajsau.jisho.model.SearchResult
import `in`.surajsau.jisho.utils.isHiragana
import `in`.surajsau.jisho.utils.isKanji
import `in`.surajsau.jisho.viewmodel.expected.BaseViewModel
import `in`.surajsau.jisho.viewmodel.expected.UiState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

@OptIn(FlowPreview::class)
public class SearchViewModel : BaseViewModel<SearchUiState>(), KoinComponent {

    private val searchForKanji: SearchForKanji = get()
    private val searchForReading: SearchForReading = get()

    private val _searchTerm = MutableStateFlow("")

    private val _results = _searchTerm
        .filterNot { it.isEmpty() }
        .debounce(300)
        .map { searchTerm ->
            when {
                searchTerm.any { it.isKanji } -> searchForKanji(text = searchTerm)
                searchTerm.all { it.isHiragana } -> searchForReading(text = searchTerm)
                else -> emptyList()
            }
        }

    override val state: StateFlow<SearchUiState> = combine(
        _searchTerm,
        _results
    ) { values ->
        val searchTerm  = values[0] as String
        val results = values[1] as List<SearchResult>

        SearchUiState(
            searchText = searchTerm,
            results = results
        )
    }.stateIn(scope, SharingStarted.WhileSubscribed(), SearchUiState())

    public fun onSearchTextChanged(text: String) {
        _searchTerm.value = text
    }
}

public data class SearchUiState(
    val searchText: String = "",
    val results: List<SearchResult> = emptyList()
): UiState
