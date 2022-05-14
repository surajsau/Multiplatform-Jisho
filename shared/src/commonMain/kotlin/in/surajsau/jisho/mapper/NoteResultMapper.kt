package `in`.surajsau.jisho.mapper

import `in`.surajsau.jisho.data.model.Note
import `in`.surajsau.jisho.model.NoteResult

fun Note.mapToNoteResult(): NoteResult
    = NoteResult(id = this.id, text = this.text)