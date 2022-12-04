package `in`.surajsau.jisho.transition

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.VectorConverter
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.round
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

context(TransitionScope)
@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.sharedTransition() = composed {
    var targetSize by remember { mutableStateOf<IntSize?>(null) }
    var sizeAnimation by remember {
        mutableStateOf<Animatable<IntSize, AnimationVector2D>?>(null)
    }

    var targetOffset by remember { mutableStateOf<IntOffset?>(null) }
    var placementOffset by remember { mutableStateOf(IntOffset.Zero) }
    var offsetAnimation by remember {
        mutableStateOf<Animatable<IntOffset, AnimationVector2D>?>(null)
    }

    LaunchedEffect(Unit) {
        launch {
            snapshotFlow { targetSize }
                .filterNotNull()
                .filterNot { it == sizeAnimation?.targetValue }
                .collect { target ->
                    sizeAnimation?.animateTo(targetValue = target)
                        ?: run {
                            sizeAnimation = Animatable(
                                initialValue = target,
                                typeConverter = IntSize.VectorConverter
                            )
                        }
                }
        }

        launch {
            snapshotFlow { targetOffset }
                .filterNotNull()
                .filterNot { it == offsetAnimation?.targetValue }
                .collect { target ->
                    offsetAnimation?.animateTo(targetValue = target)
                        ?: run {
                            offsetAnimation = Animatable(
                                initialValue = target,
                                typeConverter = IntOffset.VectorConverter
                            )
                        }
                }
        }
    }

    Modifier
        .onPlaced { lookaheadScopeCoordinates, layoutCoordinates ->
            targetOffset = lookaheadScopeCoordinates
                .localLookaheadPositionOf(layoutCoordinates)
                .round()

            placementOffset = lookaheadScopeCoordinates
                .localPositionOf(layoutCoordinates, Offset.Zero)
                .round()
        }
        .intermediateLayout { measurable, _, lookaheadSize ->
            targetSize = lookaheadSize

            val intermediateSize = sizeAnimation?.value ?: lookaheadSize
            val constraints = Constraints.fixed(
                width = intermediateSize.width,
                height = intermediateSize.height
            )
            val placeable = measurable.measure(constraints)

            layout(placeable.width, placeable.height) {
                val (x, y) = offsetAnimation?.let { it.value - placementOffset }
                    ?: (targetOffset!! - placementOffset)
                placeable.place(x, y)
            }
        }
}