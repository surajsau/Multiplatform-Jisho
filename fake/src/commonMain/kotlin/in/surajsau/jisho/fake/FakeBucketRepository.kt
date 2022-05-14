package `in`.surajsau.jisho.fake

import `in`.surajsau.jisho.data.model.Bucket
import `in`.surajsau.jisho.data.repository.BucketRepository

class FakeBucketRepository : BucketRepository {

    private val buckets = mutableListOf<Bucket>()

    private val entryBucketMapping = mutableMapOf<Long, Long>()
    private val kanjiBucketMapping = mutableMapOf<Long, Long>()

    override suspend fun getAllBuckets(): List<Bucket> = buckets

    override suspend fun getBucket(id: Long): Bucket {
        return buckets.firstOrNull { it.id == id } ?: throw Exception("Bucket not found for $id")
    }

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

    override suspend fun addEntryToBucket(entryId: Long, id: Long) {
        entryBucketMapping[entryId] = id
    }

    override suspend fun removeEntryFromBucket(entryId: Long) {
        entryBucketMapping.remove(entryId)
    }

    override suspend fun addKanjiToBucket(kanjiId: Long, id: Long) {
        kanjiBucketMapping[kanjiId] = id
    }

    override suspend fun removeKanjiFromBucket(kanjiId: Long) {
        kanjiBucketMapping.remove(kanjiId)
    }

    override suspend fun totalCountForBucket(id: Long): Int {
        return entryBucketMapping.count { (_, value) -> value == id } +
            kanjiBucketMapping.count { (_, value) -> value == id }
    }

}