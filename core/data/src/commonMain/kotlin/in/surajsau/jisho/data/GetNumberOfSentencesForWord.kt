package `in`.surajsau.jisho.data

import `in`.surajsau.jisho.data.db.Jisho
import `in`.surajsau.jisho.data.expected.DispatcherProvider
import `in`.surajsau.jisho.model.SentenceQuery
import kotlinx.coroutines.withContext

public class GetNumberOfSentencesForWord internal constructor(
    db: Jisho,
    private val dispatcherProvider: DispatcherProvider
) {
    private val queries = db.jishoQueries

    public suspend operator fun invoke(query: SentenceQuery): Int = withContext(dispatcherProvider.io) {
        return@withContext queries.getCountOfSentencesWithKeyword(query.query).executeAsOne().toInt()
    }
}
