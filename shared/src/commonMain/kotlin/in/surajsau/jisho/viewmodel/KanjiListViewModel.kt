package `in`.surajsau.jisho.viewmodel

import `in`.surajsau.jisho.expected.BaseViewModel
import `in`.surajsau.jisho.model.KanjiQuery
import `in`.surajsau.jisho.model.KanjiResult
import `in`.surajsau.jisho.usecase.GetFilteredKanjis
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class KanjiListViewModel : BaseViewModel<KanjiListViewModel.State, KanjiListViewModel.Intent, KanjiListViewModel.Effect>(), KoinComponent {

    private val getFilteredKanjis: GetFilteredKanjis = get()

    private val _query = MutableStateFlow<KanjiQuery?>(null)

    private val _items = MutableStateFlow<List<KanjiResult>>(emptyList())
    private val _effect = Channel<Effect>(Channel.UNLIMITED)

    init {
      scope.launch {
          _query
              .filterNotNull()
              .map { getFilteredKanjis(it) }
              .collect { _items.emit(it) }
      }
    }

    override val state: StateFlow<State>
        get() = combine(
            _items,
            _query,
        ) { items, query ->
            State(
                items = items,
                title = when (query) {
                    is KanjiQuery.Grade -> "Kanjis for Grade ${query.value}"
                    is KanjiQuery.Freq -> "Kanjis with frequency ${query.from}-${query.to}"
                    is KanjiQuery.All -> "All Kanjis"
                    else -> ""
                }
            )
        }.stateIn(scope, SharingStarted.WhileSubscribed(), State.Init)

    override val effect: Flow<Effect>
        get() = _effect.receiveAsFlow()

    override fun onIntent(intent: Intent) {
        when (intent) {
            is Intent.InitWith -> {
                scope.launch {
                    _query.emit(intent.query)
                }
            }

            is Intent.OnItemClicked -> {
                _effect.trySend(Effect.NavigateToDetails(intent.item.value))
            }
        }
    }

    data class State(
        val title: String,
        val items: List<KanjiResult>
    ) : VMState {
        companion object {
            val Init = State("", emptyList())
        }
    }

    sealed interface Intent : VMIntent {
        data class InitWith(val query: KanjiQuery): Intent

        data class OnItemClicked(val item: KanjiResult) : Intent
    }

    sealed interface Effect : VMEffect {
        data class NavigateToDetails(val item: String) : Effect
    }
}