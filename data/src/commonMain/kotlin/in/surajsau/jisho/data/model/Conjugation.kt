package `in`.surajsau.jisho.data.model

data class Conjugation(
  val conj: Long,
  val isNegative: Boolean,
  val isFormal: Boolean,
  val onum: Int,
  val stem: Int,
  val okurigana: String,
  val euphr: String?,
  val euphk: String?
)
