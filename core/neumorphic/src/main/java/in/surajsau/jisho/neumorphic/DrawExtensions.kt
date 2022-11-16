package `in`.surajsau.jisho.neumorphic

import android.graphics.BlurMaskFilter
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb

internal fun ContentDrawScope.drawBackground(
    shape: NeumorphicShape,
    style: NeumorphicStyle,
) {
    val elevation = style.elevation.toPx()

    drawBackground(style.lightSource, style.lightColor.toArgb(), elevation, shape)
    drawBackground(style.lightSource.opposite(), style.darkColor.toArgb(), elevation, shape)
}

internal fun ContentDrawScope.drawForeground(
    shape: NeumorphicShape,
    style: NeumorphicStyle,
) {
    val elevation = style.elevation.toPx()

    drawForeground(style.lightSource, style.darkColor.toArgb(), elevation, shape)
    drawForeground(style.lightSource.opposite(), style.lightColor.toArgb(), elevation, shape)
}

private fun ContentDrawScope.drawBackground(
    lightSource: LightSource,
    color: Int,
    elevation: Float,
    shape: NeumorphicShape
) {
    drawIntoCanvas { canvas ->
        if (elevation <= 0f) {
            return@drawIntoCanvas
        }

        val blurRadius = elevation * 0.95f
        val displacement = elevation * 0.6f

        val paint = Paint().also {
            it.asFrameworkPaint().also { nativePaint ->
                nativePaint.isAntiAlias = true
                nativePaint.isDither = true
                nativePaint.color = color
                nativePaint.maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL)
            }
        }

        val backgroundOffset = when (lightSource) {
            LightSource.TopLeft -> Offset(-displacement, -displacement)
            LightSource.TopRight -> Offset(displacement, -displacement)
            LightSource.BottomLeft -> Offset(-displacement, displacement)
            LightSource.BottomRight -> Offset(displacement, displacement)
        }

        canvas.save()
        canvas.translate(backgroundOffset.x, backgroundOffset.y)

        when (shape) {
            is NeumorphicShape.Oval -> {
                canvas.drawOval(0f, 0f, size.width, size.height, paint)
            }

            is NeumorphicShape.RoundedCorner -> {
                val cornerRadius = shape.corner.toPx()

                canvas.drawRoundRect(
                    0f,
                    0f,
                    size.width,
                    size.height,
                    cornerRadius,
                    cornerRadius,
                    paint
                )
            }
        }

        canvas.restore()
    }
}

private fun ContentDrawScope.drawForeground(
    lightSource: LightSource,
    color: Int,
    elevation: Float,
    shape: NeumorphicShape,
) {
    drawIntoCanvas { canvas ->
        if (elevation <= 0f) {
            return@drawIntoCanvas
        }

        val blurRadius = elevation * 0.6f
        val strokeWidth = elevation * 0.95f

        val paint = Paint().also {
            it.asFrameworkPaint().also { nativePaint ->
                nativePaint.isAntiAlias = true
                nativePaint.color = color
                nativePaint.strokeWidth = strokeWidth
                nativePaint.style = android.graphics.Paint.Style.STROKE
                nativePaint.maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL)
            }
        }

        val backgroundOffset = when (lightSource) {
            LightSource.TopLeft -> Offset(strokeWidth, strokeWidth)
            LightSource.TopRight -> Offset(-strokeWidth, strokeWidth)
            LightSource.BottomLeft -> Offset(strokeWidth, -strokeWidth)
            LightSource.BottomRight -> Offset(-strokeWidth, -strokeWidth)
        }

        canvas.save()

        when (shape) {
            is NeumorphicShape.Oval -> {
                val visiblePath = Path().also {
                    it.moveTo(0f, 0f)
                    it.addOval(Rect(0f, 0f, size.width, size.height))
                }

                canvas.clipPath(visiblePath)
                canvas.translate(backgroundOffset.x, backgroundOffset.y)
                canvas.drawOval(
                    -strokeWidth,
                    -strokeWidth,
                    size.width + strokeWidth,
                    size.height + strokeWidth,
                    paint
                )
            }

            is NeumorphicShape.RoundedCorner -> {
                val cornerRadius = shape.corner.toPx()

                val visiblePath = Path().also {
                    it.moveTo(0f, 0f)
                    it.addRoundRect(
                        RoundRect(0f, 0f, size.width, size.height, cornerRadius, cornerRadius)
                    )
                }

                canvas.clipPath(visiblePath)
                canvas.translate(backgroundOffset.x, backgroundOffset.y)
                canvas.drawRoundRect(
                    -strokeWidth,
                    -strokeWidth,
                    size.width + strokeWidth,
                    size.height + strokeWidth,
                    cornerRadius,
                    cornerRadius,
                    paint
                )
            }
        }
        canvas.restore()
    }
}
