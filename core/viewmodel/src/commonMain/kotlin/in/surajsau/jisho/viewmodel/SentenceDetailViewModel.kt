package `in`.surajsau.jisho.viewmodel

import `in`.surajsau.jisho.data.AddNoteForSentence
import `in`.surajsau.jisho.data.GetSentenceDetails
import `in`.surajsau.jisho.data.UpdateNotes
import `in`.surajsau.jisho.model.NoteResult
import `in`.surajsau.jisho.model.SentenceDetail
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

public class SentenceDetailViewModel : BaseViewModel<SentenceDetailUiState>(), KoinComponent {

    private val getSentenceDetails: GetSentenceDetails = get()
    private val addNoteForSentence: AddNoteForSentence = get()
    private val updateNote: UpdateNotes = get()

    private val _showEditorDialog = MutableStateFlow(false)
    private val _sentence = MutableStateFlow<SentenceDetail?>(null)

    private val _isLoading = MutableStateFlow(false)

    override val state: StateFlow<SentenceDetailUiState>
        get() = combine(
            _sentence,
            _isLoading,
            _showEditorDialog
        ) { sentence, isLoading, showEditorDialog ->
            if (sentence == null) {
                return@combine SentenceDetailUiState(isLoading = isLoading)
            }

            SentenceDetailUiState(
                japanese = sentence.japanese,
                english = sentence.english,
                note = sentence.note?.text ?: "",
                showNoteEditorDialog = showEditorDialog,
                isLoading = isLoading
            )
        }.stateIn(scope, SharingStarted.WhileSubscribed(), SentenceDetailUiState())

    public fun initWith(id: Long) {
        scope.launch {
            _sentence.value = getSentenceDetails(id)
        }
    }

    public fun onNoteClicked() {
        scope.launch {
            _showEditorDialog.value = true
        }
    }

    public fun onNoteSaved(text: String) {
        scope.launch {
            _showEditorDialog.emit(false)

            val sentence = _sentence.value ?: return@launch

            val noteId = when (val note = sentence.note) {
                null -> addNoteForSentence(id = sentence.id, text = text)
                else -> {
                    updateNote(id = note.id, text = text)
                    note.id
                }
            }

            _sentence.emit(
                _sentence.value?.copy(
                    note = NoteResult(id = noteId, text = text)
                )
            )
        }
    }
}

public data class SentenceDetailUiState(
    val japanese: String = "",
    val english: String = "",
    val note: String = "",
    val isLoading: Boolean = false,
    val showNoteEditorDialog: Boolean = false
) : UiState {

    val showAddButton: Boolean
        get() = note.isEmpty()
}
