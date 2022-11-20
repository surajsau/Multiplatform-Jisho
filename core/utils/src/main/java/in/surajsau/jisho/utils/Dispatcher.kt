package `in`.surajsau.jisho.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import `in`.surajsau.jisho.viewmodel.UnidirectionalViewModel
import `in`.surajsau.jisho.viewmodel.VMEffect
import `in`.surajsau.jisho.viewmodel.VMIntent
import `in`.surajsau.jisho.viewmodel.VMState
import kotlinx.coroutines.flow.Flow

data class Dispatcher<State : VMState, Intent : VMIntent, Effect : VMEffect>(
    val state: State,
    val intent: (Intent) -> Unit,
    val effect: Flow<Effect>
)

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
inline fun <State : VMState, Intent : VMIntent, Effect : VMEffect> dispatch(
    viewModel: UnidirectionalViewModel<State, Intent, Effect>
): Dispatcher<State, Intent, Effect> {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val intent: (Intent) -> Unit = { viewModel.onIntent(it) }

    return Dispatcher(state = state, intent = intent, effect = viewModel.effect)
}
