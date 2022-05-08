package `in`.surajsau.jisho.usecase

import `in`.surajsau.jisho.data.model.jmdict.Entry
import `in`.surajsau.jisho.data.repository.JmdictRepository

class GetEntry internal constructor(private val repository: JmdictRepository) {

    suspend operator fun invoke(id: Long): Entry {
        return repository.getEntry(id)
    }
}