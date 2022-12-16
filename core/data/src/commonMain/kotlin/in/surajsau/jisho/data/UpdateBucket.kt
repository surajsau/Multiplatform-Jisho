package `in`.surajsau.jisho.data

import `in`.surajsau.jisho.data.db.Jisho
import `in`.surajsau.jisho.utils.DispatcherProvider
import kotlinx.coroutines.withContext

public class UpdateBucket(
    private val db: Jisho,
    private val dispatcherProvider: DispatcherProvider
) {

    public suspend operator fun invoke(id: Long, name: String) {
        withContext(dispatcherProvider.io) {
            db.bucketQueries.updateBucket(id = id, name = name)
        }
    }
}