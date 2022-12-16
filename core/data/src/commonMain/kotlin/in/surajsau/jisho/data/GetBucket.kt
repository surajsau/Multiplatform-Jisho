package `in`.surajsau.jisho.data

import `in`.surajsau.jisho.data.db.Jisho
import `in`.surajsau.jisho.utils.DispatcherProvider
import `in`.surajsau.jisho.model.Bucket
import kotlinx.coroutines.withContext

public class GetBucket internal constructor(
    private val db: Jisho,
    private val dispatcherProvider: DispatcherProvider
) {

    public suspend operator fun invoke(id: Long): Bucket? = withContext(dispatcherProvider.io) {
        val bucketId = db.entryQueries.getEntry(id).executeAsOneOrNull()?.bucket_id ?: return@withContext null
        val bucket = db.bucketQueries.getBucket(bucketId).executeAsOneOrNull() ?: return@withContext null
        return@withContext Bucket(
            id = bucket.id,
            name = bucket.name ?: "Bucket $id"
        )
    }
}