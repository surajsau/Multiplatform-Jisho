package `in`.surajsau.jisho.data.model.jmdict

data class Priority(val value: String) {

    val isCommon: Boolean
        get() {
            return priorites.contains(value) || value.matches(Regex(NFXX))
        }

    companion object {
        private const val NEWS1 = "news1"
        private const val NEWS2 = "news2"
        private const val ICHI1 = "ichi1"
        private const val ICHI2 = "ichi2"
        private const val SPEC1 = "spec1"
        private const val SPEC2 = "spec2"
        private const val GAI1 = "gai1"
        private val priorites = listOf(NEWS1, NEWS2, ICHI1, ICHI2, SPEC1, SPEC2, GAI1)
        private const val NFXX = """nf\+?\d+"""
    }
}
