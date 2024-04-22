package com.example.notes.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notes.models.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao() : NotesDatabaseDao
}
