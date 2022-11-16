package `in`.surajsau.jisho.neumorphic

enum class LightSource {
    TopLeft, TopRight, BottomRight, BottomLeft;

    fun opposite(): LightSource = when (this) {
        TopLeft -> BottomRight
        TopRight -> BottomLeft
        BottomRight -> TopLeft
        BottomLeft -> TopRight
    }
}