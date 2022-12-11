package `in`.surajsau.jisho.viewmodel

import `in`.surajsau.jisho.data.GetSentencesForWord
import `in`.surajsau.jisho.model.SentenceQuery
import `in`.surajsau.jisho.model.SentenceResult
import `in`.surajsau.jisho.viewmodel.expected.BaseViewModel
import `in`.surajsau.jisho.viewmodel.expected.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

public class SentenceListViewModel : BaseViewModel<SentenceListUiState>(), KoinComponent {

    private val getSentencesForWord: GetSentencesForWord = get()

    private val _sentences = MutableStateFlow<List<SentenceResult>>(emptyList())

    override val state: StateFlow<SentenceListUiState>
        get() = _sentences
            .map { SentenceListUiState(sentences = it) }
            .stateIn(scope, SharingStarted.WhileSubscribed(), SentenceListUiState())

    public fun initWith(word: String) {
        scope.launch {
            _sentences.value = getSentencesForWord(query = SentenceQuery(word), count = -1)
        }
    }
}

public data class SentenceListUiState(
    val sentences: List<SentenceResult> = emptyList()
) : UiState
