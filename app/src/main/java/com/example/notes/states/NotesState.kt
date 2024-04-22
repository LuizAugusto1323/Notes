package com.example.notes.states

import com.example.notes.models.Notes

data class NotesState(
    val notesList: List<Notes> = emptyList()
)