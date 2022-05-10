package `in`.surajsau.jisho.viewmodel

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface VMState
interface VMIntent
interface VMEffect

interface UnidirectionalViewModel<S : VMState, I : VMIntent, E : VMEffect> {
    val state: StateFlow<S>
    fun onIntent(intent: I)
    val effect: Flow<E>
}

data class Dispatcher<State, Intent, Effect>(
    val state: State,
    val intent: (Intent) -> Unit,
    val effect: Flow<Effect>
)
