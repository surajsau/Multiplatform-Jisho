package `in`.surajsau.jisho.data.real

import `in`.surajsau.jisho.data.db.Jisho
import `in`.surajsau.jisho.data.expected.DispatcherProvider
import `in`.surajsau.jisho.data.model.Bucket
import `in`.surajsau.jisho.data.repository.BucketRepository
import kotlinx.coroutines.withContext

class BucketRepositoryImpl(
    db: Jisho,
    private val dispatcherProvider: DispatcherProvider
) : BucketRepository {

    private val queries = db.jishoQueries

    override suspend fun getBucket(id: Long): Bucket = withContext(dispatcherProvider.io) {
        throw Exception()
    }

    override suspend fun addBucket(name: String): Long = withContext(dispatcherProvider.io) {
        queries.insertBucket(name = name)
        return@withContext queries.getLastInsertedBucketId().executeAsOne()
    }

    override suspend fun removeBucket(id: Long) = withContext(dispatcherProvider.io) {
        queries.removeBucket(id = id)
    }

    override suspend fun updateBucket(id: Long, name: String) = withContext(dispatcherProvider.io) {
        queries.updateBucket(name = name, id = id)
    }

    override suspend fun addEntryToBucket(entryId: Long, id: Long) = withContext(dispatcherProvider.io) {
        queries.updateEntryBucket(bucket = id, id = entryId)
    }

    override suspend fun removeEntryFromBucket(entryId: Long) = withContext(dispatcherProvider.io) {
        queries.updateEntryBucket(bucket = null, id = entryId)
    }

    override suspend fun addKanjiToBucket(kanjiId: Long, id: Long) = withContext(dispatcherProvider.io) {
        queries.updateKanjiBucket(bucket = id, id = kanjiId)
    }

    override suspend fun removeKanjiFromBucket(kanjiId: Long) = withContext(dispatcherProvider.io) {
        queries.updateKanjiBucket(bucket = null, id = kanjiId)
    }

    override suspend fun getAllBuckets(): List<Bucket> = withContext(dispatcherProvider.io) {
        return@withContext queries.getAllBuckets()
            .executeAsList()
            .map { Bucket(id = it.id, name = it.name!!) }
    }

    override suspend fun totalCountForBucket(id: Long): Int = withContext(dispatcherProvider.io) {
        return@withContext 0
    }

}
