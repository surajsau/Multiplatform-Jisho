package `in`.surajsau.jisho.viewmodel

import `in`.surajsau.jisho.data.GetAllForJlptLevel
import `in`.surajsau.jisho.model.SearchResult
import `in`.surajsau.jisho.viewmodel.expected.BaseViewModel
import `in`.surajsau.jisho.viewmodel.expected.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

public class JlptListViewModel : BaseViewModel<JlptListUiState>(), KoinComponent {

    private val getAllForJlptLevel: GetAllForJlptLevel = get()

    private val _items = MutableStateFlow<List<SearchResult>>(emptyList())
    private val _title = MutableStateFlow("")
    private val _isLoading = MutableStateFlow(false)

    override val state: StateFlow<JlptListUiState>
        get() = combine(
            _title,
            _items,
            _isLoading
        ) { title, items, isLoading ->
            JlptListUiState(
                title = title,
                items = items,
                isLoading = isLoading
            )
        }.stateIn(scope, SharingStarted.WhileSubscribed(), JlptListUiState())

    public fun initWith(level: Int) {
        scope.launch {
            _title.emit("JLPT N${level}")
            _isLoading.emit(true)

            val items = getAllForJlptLevel(level)

            _isLoading.emit(false)
            _items.emit(items)
        }
    }
}

public data class JlptListUiState(
    val title: String = "",
    val items: List<SearchResult> = emptyList(),
    val isLoading: Boolean = false,
) : UiState
