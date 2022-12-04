package `in`.surajsau.jisho.transition

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.layout.LookaheadLayoutScope

@OptIn(ExperimentalComposeUiApi::class)
class TransitionScope constructor(
    private val scope: LookaheadLayoutScope
): LookaheadLayoutScope by scope