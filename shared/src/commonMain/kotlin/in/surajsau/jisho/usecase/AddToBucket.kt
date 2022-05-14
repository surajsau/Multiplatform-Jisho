package `in`.surajsau.jisho.usecase

import `in`.surajsau.jisho.data.repository.BucketRepository
import `in`.surajsau.jisho.model.BucketQuery

internal class AddToBucket constructor(
    private val repository: BucketRepository
) {

    suspend operator fun invoke(query: BucketQuery) {
        when (query) {
            is BucketQuery.Entry -> repository.addEntryToBucket(entryId = query.id, id = query.bucketId)
            is BucketQuery.Kanji -> repository.addKanjiToBucket(kanjiId = query.id, id = query.bucketId)
        }
    }
}