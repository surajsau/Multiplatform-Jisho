package `in`.surajsau.jisho.usecase

import `in`.surajsau.jisho.data.repository.BucketRepository
import `in`.surajsau.jisho.mapper.mapToBucketResult
import `in`.surajsau.jisho.model.BucketResult

internal class GetAllBuckets constructor(
    private val repository: BucketRepository,
) {

    suspend operator fun invoke(): List<BucketResult> {
        return repository.getAllBuckets()
            .map {
                val count = repository.totalCountForBucket(id = it.id)
                it.mapToBucketResult(count = count)
            }
    }
}