package `in`.surajsau.jisho.utils

sealed interface Optional<out T> {
    val value: T?

    val isEmpty: Boolean
        get() = this is Empty

    fun <S> map(block: (T?) -> S?): Optional<S> {
        if (this is Empty) return Empty
        return of(block.invoke(value))
    }

    object Empty : Optional<Nothing> {
        override val value: Nothing?
            get() = null
    }

    data class Some<T>(override val value: T) : Optional<T>

    companion object {
        fun <T> of(value: T?): Optional<T> {
            if (value == null) {
                return Empty
            }

            return Some(value)
        }
    }
}
