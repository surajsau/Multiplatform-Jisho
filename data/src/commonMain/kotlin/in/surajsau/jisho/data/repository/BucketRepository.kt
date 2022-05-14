package `in`.surajsau.jisho.data.repository

import `in`.surajsau.jisho.data.model.Bucket

interface BucketRepository {

    suspend fun getBucket(id: Long): Bucket

    suspend fun getAllBuckets(): List<Bucket>
    suspend fun addBucket(name: String): Long
    suspend fun removeBucket(id: Long)
    suspend fun updateBucket(id: Long, name: String)

    suspend fun addEntryToBucket(entryId: Long, id: Long)
    suspend fun removeEntryFromBucket(entryId: Long)
    suspend fun addKanjiToBucket(kanjiId: Long, id: Long)
    suspend fun removeKanjiFromBucket(kanjiId: Long)

    suspend fun totalCountForBucket(id: Long): Int
}
