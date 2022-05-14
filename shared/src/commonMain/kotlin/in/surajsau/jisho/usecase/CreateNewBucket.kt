package `in`.surajsau.jisho.usecase

import `in`.surajsau.jisho.data.repository.BucketRepository

internal class CreateNewBucket constructor(
    private val repository: BucketRepository
) {

    suspend operator fun invoke(name: String): Long {
        return repository.addBucket(name = name)
    }
}