package `in`.surajsau.jisho.viewmodel

import `in`.surajsau.jisho.expected.BaseViewModel
import `in`.surajsau.jisho.model.BucketResult
import `in`.surajsau.jisho.usecase.CreateNewBucket
import `in`.surajsau.jisho.usecase.RemoveBucket
import `in`.surajsau.jisho.usecase.RenameBucket
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class BucketsListViewModel : BaseViewModel<BucketsListViewModel.State, BucketsListViewModel.Intent, BucketsListViewModel.Effect>(), KoinComponent {

    private val addBucket: CreateNewBucket = get()
    private val removeBucket: RemoveBucket = get()
    private val renameBucket: RenameBucket = get()

    private val _buckets = MutableStateFlow<List<BucketResult>>(emptyList())

    private val _effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val state: StateFlow<State>
        get() = _buckets
            .map { State(buckets = it) }
            .stateIn(scope, SharingStarted.WhileSubscribed(), State.Init)

    override val effect: Flow<Effect>
        get() = _effectChannel.receiveAsFlow()

    override fun onIntent(intent: Intent) {
        when (intent) {
            is Intent.AddBucket -> {
                scope.launch { addBucket(name = intent.name) }
            }

            is Intent.RemoveBucket -> {
                scope.launch { removeBucket(id = intent.id) }
            }

            is Intent.RenameBucket -> {
                scope.launch {
                    renameBucket(id = intent.id, name = intent.name)
                    _buckets.update {
                        val index = it.indexOfFirst { bucket -> bucket.id == intent.id }

                        it.toMutableList().apply {
                            this[index] = this[index].copy(name = intent.name)
                        }
                    }
                }
            }
        }
    }

    data class State(
        val buckets: List<BucketResult>,
    ): VMState {
        companion object {
            val Init: State = State(emptyList())
        }
    }

    sealed interface Intent: VMIntent {
        data class AddBucket(val name: String): Intent
        data class RemoveBucket(val id: Long): Intent
        data class RenameBucket(val id: Long, val name: String): Intent
    }

    sealed interface Effect: VMEffect
}