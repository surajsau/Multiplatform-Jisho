package `in`.surajsau.jisho.utils

public sealed interface Optional<out T> {
    public val value: T?

    public val isEmpty: Boolean
        get() = this is Empty

    public fun <S> map(block: (T?) -> S?): Optional<S> {
        if (this is Empty) return Empty
        return of(block.invoke(value))
    }

    public object Empty : Optional<Nothing> {
        override val value: Nothing?
            get() = null
    }

    public data class Some<T>(override val value: T) : Optional<T>

    public companion object {
        public fun <T> of(value: T?): Optional<T> {
            if (value == null) {
                return Empty
            }

            return Some(value)
        }
    }
}
