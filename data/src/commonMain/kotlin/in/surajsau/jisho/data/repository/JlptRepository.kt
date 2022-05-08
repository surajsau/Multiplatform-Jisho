package `in`.surajsau.jisho.data.repository

interface JlptRepository {

  suspend fun getForLevel(level: Long): List<String>
}