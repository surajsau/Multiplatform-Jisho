package `in`.surajsau.jisho.data

import `in`.surajsau.jisho.data.db.Jisho
import `in`.surajsau.jisho.utils.DispatcherProvider
import `in`.surajsau.jisho.model.SentenceQuery
import kotlinx.coroutines.withContext

public class GetNumberOfSentencesForWord internal constructor(
    private val db: Jisho,
    private val dispatcherProvider: DispatcherProvider
) {
    public suspend operator fun invoke(query: SentenceQuery): Int = withContext(dispatcherProvider.io) {
        return@withContext db.sentenceQueries.getCountOfSentencesWithKeyword(query.query).executeAsOne().toInt()
    }
}
