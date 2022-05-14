package `in`.surajsau.jisho.usecase

import `in`.surajsau.jisho.data.repository.BucketRepository
import `in`.surajsau.jisho.model.BucketQuery

internal class RemoveFromBucket constructor(
    private val repository: BucketRepository
) {

    suspend operator fun invoke(query: BucketQuery) {
        when (query) {
            is BucketQuery.Kanji -> repository.removeKanjiFromBucket(kanjiId = query.id)
            is BucketQuery.Entry -> repository.removeEntryFromBucket(entryId = query.id)
        }
    }
}