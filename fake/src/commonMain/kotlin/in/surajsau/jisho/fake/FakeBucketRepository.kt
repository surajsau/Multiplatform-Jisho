package `in`.surajsau.jisho.fake

import `in`.surajsau.jisho.data.model.Bucket
import `in`.surajsau.jisho.data.repository.BucketRepository

class FakeBucketRepository : BucketRepository {

    private val buckets = mutableListOf<Bucket>()

    override suspend fun getAllBuckets(): List<Bucket> = buckets

    override suspend fun addBucket(name: String): Long {
        val id = (buckets.lastOrNull()?.id ?: 0L) + 1L
        buckets.add(Bucket(id = id, name = name))
        return id
    }

    override suspend fun removeBucket(id: Long) {
        buckets.removeAll { it.id == id }
    }

    override suspend fun updateBucket(id: Long, name: String) {
        val index = buckets.indexOfFirst { it.id == id }
        if (index != -1)
            buckets[index] = buckets[index].copy(name = name)
    }

}