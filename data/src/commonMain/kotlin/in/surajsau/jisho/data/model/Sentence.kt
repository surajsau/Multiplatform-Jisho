package `in`.surajsau.jisho.data.model

data class Sentence(
  val id: Long,
  val jp: String,
  val en: String,
  val noteId: Long?,
)
