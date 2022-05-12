package `in`.surajsau.jisho.data.real

import `in`.surajsau.jisho.data.db.Jisho
import `in`.surajsau.jisho.data.expected.DispatcherProvider
import `in`.surajsau.jisho.data.model.Bucket
import `in`.surajsau.jisho.data.repository.BucketRepository

class BucketRepositoryImpl(
    db: Jisho,
    private val dispatcherProvider: DispatcherProvider
) : BucketRepository {

    private val queries = db.jishoQueries

    override suspend fun addBucket(name: String): Long {
        return 0L
    }

    override suspend fun removeBucket(id: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun updateBucket(id: Long, name: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllBuckets(): List<Bucket> {
        return emptyList()
    }
}
