package `in`.surajsau.jisho.viewmodel

import `in`.surajsau.jisho.expected.BaseViewModel
import `in`.surajsau.jisho.model.NoteResult
import `in`.surajsau.jisho.model.SentenceDetail
import `in`.surajsau.jisho.usecase.AddNoteForSentence
import `in`.surajsau.jisho.usecase.GetSentenceDetails
import `in`.surajsau.jisho.usecase.UpdateNotes
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

class SentenceDetailViewModel : BaseViewModel<SentenceDetailViewModel.State, SentenceDetailViewModel.Intent, SentenceDetailViewModel.Effect>(), KoinComponent {

    private val getSentenceDetails: GetSentenceDetails = get()
    private val addNoteForSentence: AddNoteForSentence = get()
    private val updateNote: UpdateNotes = get()

    private val _effectChannel = Channel<Effect>(Channel.UNLIMITED)

    private val _showEditorDialog = MutableStateFlow(false)
    private val _sentence = MutableStateFlow<SentenceDetail?>(null)

    private val _isLoading = MutableStateFlow(false)

    override val state: StateFlow<State>
        get() = combine(
            _sentence,
            _showEditorDialog
        ) { sentence, showEditorDialog ->
            if (sentence == null) {
                return@combine State.Init.copy(isLoading = true)
            }

            State(
                japanese = sentence.japanese,
                english = sentence.english,
                note = sentence.note?.text ?: "",
                showNoteEditorDialog = showEditorDialog,
                isLoading = false
            )
        }.stateIn(scope, SharingStarted.WhileSubscribed(), State.Init)

    override val effect: Flow<Effect>
        get() = _effectChannel.receiveAsFlow()

    override fun onIntent(intent: Intent) {
        when (intent) {
            is Intent.InitWith -> {
                scope.launch {
                    _sentence.emit(getSentenceDetails(intent.id))
                }
            }

            is Intent.NoteSaved -> {
                val sentence = _sentence.value ?: return

                scope.launch {
                    _showEditorDialog.emit(false)

                    val noteId = when (val note = sentence.note) {
                        null -> {
                            addNoteForSentence(id = sentence.id, text = intent.text)
                        }

                        else -> {
                            updateNote(id = note.id, text = intent.text)
                            note.id
                        }
                    }

                    _sentence.emit(
                        _sentence.value?.copy(
                            note = NoteResult(id = noteId, text = intent.text)
                        )
                    )
                }
            }

            is Intent.NoteClicked -> {
                scope.launch {
                    _showEditorDialog.emit(true)
                }
            }
        }
    }

    data class State(
        val japanese: String,
        val english: String,
        val note: String,
        val isLoading: Boolean,
        val showNoteEditorDialog: Boolean
    ) : VMState {

        val showAddButton: Boolean
            get() = note.isEmpty()

        companion object {
            val Init = State("", "", "", false, false)
        }
    }

    sealed interface Intent : VMIntent {
        data class InitWith(val id: Long) : Intent

        data class NoteClicked(val text: String) : Intent
        data class NoteSaved(val text: String) : Intent
    }
    sealed interface Effect : VMEffect
}
