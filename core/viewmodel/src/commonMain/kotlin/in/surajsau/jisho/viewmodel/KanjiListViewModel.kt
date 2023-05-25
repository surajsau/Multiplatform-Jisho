package `in`.surajsau.jisho.viewmodel

import `in`.surajsau.jisho.data.GetFilteredKanjis
import `in`.surajsau.jisho.model.KanjiQuery
import `in`.surajsau.jisho.model.KanjiResult
import `in`.surajsau.jisho.viewmodel.expected.BaseViewModel
import `in`.surajsau.jisho.viewmodel.expected.UiState
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

public class KanjiListViewModel : BaseViewModel<KanjiListUiState>(), KoinComponent {

    private val getFilteredKanjis: GetFilteredKanjis = get()

    private val _query = MutableStateFlow<KanjiQuery?>(null)
    private val _items = MutableStateFlow<List<KanjiResult>>(emptyList())

    override val state: StateFlow<KanjiListUiState>
        get() = combine(
            _items.filterNot { it.isEmpty() },
            _query.filterNotNull(),
        ) { items, query ->
            val state = KanjiListUiState(
                items = items,
                title = when (query) {
                    is KanjiQuery.Grade -> "Kanjis for Grade ${query.grade + 1}"
                    is KanjiQuery.AllSchool -> "All school Kanjis"
                    is KanjiQuery.Freq -> "Kanjis with frequency ${query.from + 1}-${query.to + 1}"
                    is KanjiQuery.All -> "All Kanjis"
                }
            )
            Napier.d("State: $state")
            state
        }.stateIn(scope, SharingStarted.WhileSubscribed(), KanjiListUiState())

    public fun initWith(query: KanjiQuery) {
        scope.launch {
            _query.value = query
            _items.value = getFilteredKanjis(query)
        }
    }
}

public data class KanjiListUiState(
    val title: String = "",
    val items: List<KanjiResult> = emptyList()
) : UiState
