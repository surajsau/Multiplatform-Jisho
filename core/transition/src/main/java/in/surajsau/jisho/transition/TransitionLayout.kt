package `in`.surajsau.jisho.transition

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LookaheadLayout
import androidx.compose.ui.layout.MeasurePolicy

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TransitionLayout(
    modifier: Modifier = Modifier,
    content: @Composable TransitionScope.() -> Unit
) {
    LookaheadLayout(
        modifier = modifier,
        content = { content(TransitionScope(this)) },
        measurePolicy = DefaultMeasurePolicy
    )
}

private val DefaultMeasurePolicy: MeasurePolicy = MeasurePolicy { measurables, constraints ->
    val placeables = measurables.map { it.measure(constraints) }
    val maxWidth = placeables.maxOf { it.width }
    val maxHeight = placeables.maxOf { it.height }

    layout(maxWidth, maxHeight) {
        placeables.forEach {
            it.place(0, 0)
        }
    }
}