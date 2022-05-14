package `in`.surajsau.jisho.usecase

import `in`.surajsau.jisho.data.repository.BucketRepository

internal class RemoveBucket constructor(
    private val repository: BucketRepository
) {

    suspend operator fun invoke(id: Long) {
        repository.removeBucket(id = id)
    }
}