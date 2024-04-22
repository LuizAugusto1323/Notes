package com.example.notes.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.models.Notes
import com.example.notes.room.NotesDatabaseDao
import com.example.notes.states.NotesState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NotesViewModel(private val dao: NotesDatabaseDao) : ViewModel() {
    var state by mutableStateOf(NotesState())
        private set

    init {
        viewModelScope.launch {
            dao.getNotes().collectLatest {
                state = state.copy(
                    notesList = it
                )
            }
        }
    }

    fun insertNote(note: Notes) {
        viewModelScope.launch {
            dao.insertNote(note = note)
        }
    }

    fun updateNote(note: Notes) {
        viewModelScope.launch {
            dao.updateNote(note = note)
        }
    }

    fun deleteNote(note: Notes) {
        viewModelScope.launch {
            dao.deleteNote(note = note)
        }
    }
}
