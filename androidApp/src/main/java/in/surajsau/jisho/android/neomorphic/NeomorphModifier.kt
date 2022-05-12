package `in`.surajsau.jisho.android.neomorphic

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.android.ui.theme.darkShadow
import `in`.surajsau.jisho.android.ui.theme.lightShadow
import `in`.surajsau.jisho.android.ui.theme.neomorphicBackground

fun Modifier.clickableNeomorph(
    lightShadowColor: Color? = null,
    darkShadowColor: Color? = null,
    elevation: Dp = 4.dp,
    lightSource: LightSource = LightSource.TopLeft,
    isPressed: Boolean = false,
    shape: NeomorphicShape = NeomorphicShape.RoundedCorner(4.dp),
    onClick: () -> Unit = {},
): Modifier = composed {
    var isPressedDown by remember { mutableStateOf(false) }

    val currentElevation by animateDpAsState(
        targetValue = if (isPressedDown) 0.dp else elevation,
        animationSpec = tween(durationMillis = 100, easing = FastOutLinearInEasing)
    )

    this
        .pointerInput(Unit) {
            detectTapGestures(
                onTap = { onClick() },
                onPress = {
                    isPressedDown = true

                    if (tryAwaitRelease()) {
                        isPressedDown = false
                    }
                }
            )
        }
        .then(
            Modifier.neomorph(
                lightShadowColor = lightShadowColor,
                darkShadowColor = darkShadowColor,
                elevation = currentElevation,
                lightSource = lightSource,
                isPressed = isPressed,
                shape = shape
            )
        )
}

fun Modifier.neomorph(
    lightShadowColor: Color? = null,
    darkShadowColor: Color? = null,
    elevation: Dp = 4.dp,
    lightSource: LightSource = LightSource.TopLeft,
    isPressed: Boolean = false,
    shape: NeomorphicShape = NeomorphicShape.RoundedCorner(4.dp)
): Modifier = composed {
    val lightColor = lightShadowColor ?: MaterialTheme.colors.lightShadow
    val darkColor = darkShadowColor ?: MaterialTheme.colors.darkShadow

    this.then(object : DrawModifier {
        override fun ContentDrawScope.draw() {
            val style = NeomorphicStyle(lightColor, darkColor, elevation, lightSource)
            when {
                isPressed -> drawForeground(shape, style)
                else -> drawBackground(shape, style)
            }
            drawContent()
        }
    })
        .background(
            color = if (isPressed) {
                Color.Transparent
            } else MaterialTheme.colors.neomorphicBackground,
            shape = when (shape) {
                is NeomorphicShape.Oval -> CircleShape
                is NeomorphicShape.RoundedCorner -> RoundedCornerShape(size = shape.corner)
            }
        )
}
