package `in`.surajsau.jisho.data.model.jmdict

data class JReading(
    val value: String,
    val info: List<Info>,
    val priority: List<Priority>,
    val isNotTrueReading: Boolean,
    val restriction: List<Restriction>,
)
