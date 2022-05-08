package `in`.surajsau.jisho.viewmodel

import `in`.surajsau.jisho.expected.BaseViewModel
import `in`.surajsau.jisho.model.SentenceQuery
import `in`.surajsau.jisho.model.SentenceResult
import `in`.surajsau.jisho.usecase.GetSentencesForWord
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class SentenceListViewModel: BaseViewModel<SentenceListViewModel.State, SentenceListViewModel.Intent, SentenceListViewModel.Effect>(), KoinComponent {

  private val getSentencesForWord: GetSentencesForWord = get()

  private val _sentences = MutableStateFlow<List<SentenceResult>>(emptyList())
  private val _effectChannel = Channel<Effect>(Channel.UNLIMITED)

  override val state: StateFlow<State>
    get() = _sentences
      .map { State(sentences = it) }
      .stateIn(scope, SharingStarted.WhileSubscribed(), State.Init)

  override val effect: Flow<Effect>
    get() = _effectChannel.receiveAsFlow()

  override fun onIntent(intent: Intent) {
    when (intent) {
      is Intent.InitWith -> {
        scope.launch {
          val sentences = getSentencesForWord(query = SentenceQuery(intent.word), count = -1)
          _sentences.emit(sentences)
        }
      }
    }
  }

  data class State(
    val sentences: List<SentenceResult>
  ): VMState {

    companion object {
      val Init = State(emptyList())
    }
  }

  sealed interface Intent: VMIntent {
    data class InitWith(val word: String): Intent
  }

  sealed interface Effect: VMEffect
}