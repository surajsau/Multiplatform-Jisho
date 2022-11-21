package `in`.surajsau.jisho.viewmodel

import `in`.surajsau.jisho.data.GetSentencesForWord
import `in`.surajsau.jisho.model.SentenceQuery
import `in`.surajsau.jisho.model.SentenceResult
import `in`.surajsau.jisho.viewmodel.expected.BaseViewModel
import `in`.surajsau.jisho.viewmodel.expected.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

public class SentenceListViewModel : BaseViewModel<SentenceListState>(), KoinComponent {

    private val getSentencesForWord: GetSentencesForWord = get()

    private val _sentences = MutableStateFlow<List<SentenceResult>>(emptyList())

    override val state: StateFlow<SentenceListState>
        get() = _sentences
            .map { SentenceListState(sentences = it) }
            .stateIn(scope, SharingStarted.WhileSubscribed(), SentenceListState())

    public fun initWith(word: String) {
        scope.launch {
            _sentences.value = getSentencesForWord(query = SentenceQuery(word), count = -1)
        }
    }
}

public data class SentenceListState(
    val sentences: List<SentenceResult> = emptyList()
) : State
