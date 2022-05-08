package `in`.surajsau.jisho.viewmodel

import `in`.surajsau.jisho.expected.BaseViewModel
import `in`.surajsau.jisho.model.JlptResult
import `in`.surajsau.jisho.usecase.GetAllForJlptLevel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class JlptListViewModel : BaseViewModel<JlptListViewModel.State, JlptListViewModel.Intent, JlptListViewModel.Effect>(), KoinComponent {

  private val getAllForJlptLevel: GetAllForJlptLevel = get()

  private val _items = MutableStateFlow<List<JlptResult>>(emptyList())
  private val _effectChannel = Channel<Effect>(Channel.UNLIMITED)
  private val _isLoading = MutableStateFlow(false)

  override val state: StateFlow<State>
    get() = combine(_items, _isLoading) { items, isLoading ->
      State(
        items = items,
        isLoading = isLoading
      )
    }.stateIn(scope, SharingStarted.WhileSubscribed(), State.Init)

  override fun onIntent(intent: Intent) {
    when (intent) {
      is Intent.OnItemClicked -> {
        _effectChannel.trySend(Effect.NavigateToDetails(intent.value))
      }

      is Intent.InitWith -> {
        scope.launch {
          _isLoading.emit(true)

          val items = getAllForJlptLevel(intent.level)
          _isLoading.emit(false)
          _items.emit(items)
        }
      }
    }
  }

  override val effect: Flow<Effect>
    get() = _effectChannel.receiveAsFlow()

  data class State(
    val items: List<JlptResult>,
    val isLoading: Boolean,
  ): VMState {

    companion object {
      val Init = State(emptyList(), false)
    }
  }

  sealed interface Intent: VMIntent {
    data class InitWith(val level: Int): Intent

    data class OnItemClicked(val value: String): Intent
  }

  sealed interface Effect: VMEffect {
    data class NavigateToDetails(val item: String): Effect
  }
}