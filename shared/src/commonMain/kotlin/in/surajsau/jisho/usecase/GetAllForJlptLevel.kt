package `in`.surajsau.jisho.usecase

import `in`.surajsau.jisho.data.repository.JlptRepository
import `in`.surajsau.jisho.model.JlptResult

internal class GetAllForJlptLevel (private val repository: JlptRepository) {

  suspend operator fun invoke(level: Int): List<JlptResult> {
    return repository.getForLevel(level = level.toLong())
      .map { JlptResult(it) }
  }
}