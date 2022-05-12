package `in`.surajsau.jisho.viewmodel

import `in`.surajsau.jisho.expected.BaseViewModel
import `in`.surajsau.jisho.model.BucketResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.koin.core.component.KoinComponent

class BucketsListViewModel : BaseViewModel<BucketsListViewModel.State, BucketsListViewModel.Intent, BucketsListViewModel.Effect>(), KoinComponent {

    val _buckets = MutableStateFlow<List<BucketResult>>(emptyList())

    override val state: StateFlow<State>
        get() = _buckets
            .map { State(buckets = it) }
            .stateIn(scope, SharingStarted.WhileSubscribed(), State.Init)

    data class State(
        val buckets: List<BucketResult>,
    ): VMState {
        companion object {
            val Init: State = State(emptyList())
        }
    }

    sealed interface Intent: VMIntent

    sealed interface Effect: VMEffect
}