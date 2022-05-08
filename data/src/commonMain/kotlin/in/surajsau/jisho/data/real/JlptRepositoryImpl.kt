package `in`.surajsau.jisho.data.real

import `in`.surajsau.jisho.data.db.Jisho
import `in`.surajsau.jisho.data.expected.DispatcherProvider
import `in`.surajsau.jisho.data.repository.JlptRepository
import kotlinx.coroutines.withContext

class JlptRepositoryImpl(
  jisho: Jisho,
  private val dispatcherProvider: DispatcherProvider,
) : JlptRepository {

  private val queries = jisho.jishoQueries

  override suspend fun getForLevel(level: Long): List<String> = withContext(dispatcherProvider.io) {
    return@withContext queries.getAllForJlpt(level).executeAsList().map { it.word!! }
  }
}