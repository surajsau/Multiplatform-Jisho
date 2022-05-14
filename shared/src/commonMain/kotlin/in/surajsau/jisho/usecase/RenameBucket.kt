package `in`.surajsau.jisho.usecase

import `in`.surajsau.jisho.data.repository.BucketRepository

internal class RenameBucket constructor(
    private val repository: BucketRepository
) {

    suspend operator fun invoke(id: Long, name: String) {
        repository.updateBucket(id = id, name = name)
    }
}