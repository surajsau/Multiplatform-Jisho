package `in`.surajsau.jisho.mapper

import `in`.surajsau.jisho.data.model.Bucket
import `in`.surajsau.jisho.model.BucketResult

fun Bucket.mapToBucketResult(count: Int): BucketResult
    = BucketResult(id = this.id, name = this.name, count = count)