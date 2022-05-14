package `in`.surajsau.jisho.usecase

import `in`.surajsau.jisho.data.repository.BucketRepository
import `in`.surajsau.jisho.data.repository.JmdictRepository
import `in`.surajsau.jisho.data.repository.NotesRepository
import `in`.surajsau.jisho.mapper.mapToBucketResult
import `in`.surajsau.jisho.mapper.mapToNoteResult
import `in`.surajsau.jisho.model.EntryResult

internal class GetEntry constructor(
    private val jmdictRepository: JmdictRepository,
    private val notesRepository: NotesRepository,
    private val bucketRepository: BucketRepository,
) {

    suspend operator fun invoke(id: Long): EntryResult {
        val entry = jmdictRepository.getEntry(id = id)
        val note = entry.noteId?.let { notesRepository.getNote(id = it) }
        val bucket = entry.bucketId?.let { bucketRepository.getBucket(id = it) }

        return EntryResult(
            id = entry.id,
            kanjis = entry.kanjis,
            readings = entry.readings,
            senses = entry.senses,
            note = note?.mapToNoteResult(),

            // count isn't necessary except in bucket lists where it is already being queried for
            // Check GetAllBuckets
            bucket = bucket?.mapToBucketResult(count = -1)
        )
    }
}