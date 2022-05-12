package `in`.surajsau.jisho.data.repository

import `in`.surajsau.jisho.data.model.Bucket

interface BucketRepository {

    suspend fun getAllBuckets(): List<Bucket>
    suspend fun addBucket(name: String): Long
    suspend fun removeBucket(id: Long)
    suspend fun updateBucket(id: Long, name: String)
}
