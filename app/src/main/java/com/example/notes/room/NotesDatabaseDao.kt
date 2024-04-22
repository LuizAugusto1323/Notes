package com.example.notes.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.notes.models.Notes
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDatabaseDao {
    @Query("SELECT * FROM notes")
    fun getNotes() : Flow<List<Notes>>

    @Query("SELECT * FROM notes WHERE id = :id")
    fun getNotesById(id: Int) : Flow<Notes>

    @Insert
    suspend fun insertNote(note: Notes)

    @Update
    suspend fun updateNote(note: Notes)

    @Delete
    suspend fun deleteNote(note: Notes)
}
