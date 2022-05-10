package `in`.surajsau.jisho.viewmodel

import `in`.surajsau.jisho.expected.BaseViewModel
import `in`.surajsau.jisho.model.KanjiQuery
import `in`.surajsau.jisho.model.KanjiResult
import `in`.surajsau.jisho.usecase.GetFilteredKanjis
import `in`.surajsau.jisho.utils.Optional
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class KanjiListViewModel : BaseViewModel<KanjiListViewModel.State, KanjiListViewModel.Intent, KanjiListViewModel.Effect>(), KoinComponent {

    private val getFilteredKanjis: GetFilteredKanjis = get()

    private val _query = MutableStateFlow<Optional<KanjiQuery>>(Optional.Empty)

    private val _items = MutableStateFlow<List<KanjiResult>>(emptyList())
    private val _effect = Channel<Effect>(Channel.UNLIMITED)

    override val state: StateFlow<State>
        get() = combine(
            _items.filterNot { it.isEmpty() },
            _query.filterNot { it.isEmpty }.map { it.value!! },
        ) { items, query ->
            State(
                items = items,
                title = when (query) {
                    is KanjiQuery.Grade -> "Kanjis for Grade ${query.grade}"
                    is KanjiQuery.AllSchool -> "All school Kanjis"
                    is KanjiQuery.Freq -> "Kanjis with frequency ${query.from}-${query.to}"
                    is KanjiQuery.All -> "All Kanjis"
                }
            )
        }.stateIn(scope, SharingStarted.WhileSubscribed(), State.Init)

    override val effect: Flow<Effect>
        get() = _effect.receiveAsFlow()

    override fun onIntent(intent: Intent) {
        when (intent) {
            is Intent.InitWith -> {
                scope.launch {
                    _query.emit(Optional.of(intent.query))

                    val results = getFilteredKanjis(intent.query)
                    _items.emit(results)
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
        data class InitWith(val query: KanjiQuery) : Intent

        data class OnItemClicked(val item: KanjiResult) : Intent
    }

    sealed interface Effect : VMEffect {
        data class NavigateToDetails(val item: String) : Effect
    }
}
