package com.example.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.example.notes.navigation.NavManager
import com.example.notes.room.NotesDatabase
import com.example.notes.ui.theme.CRUD_WITH_ROOMTheme
import com.example.notes.viewModels.NotesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CRUD_WITH_ROOMTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val database = Room.databaseBuilder(context = this, klass = NotesDatabase::class.java, name = "db_users").build()
                    val dao = database.notesDao()
                    val viewModel = NotesViewModel(dao = dao)

                    NavManager(viewModel = viewModel)
                }
            }
        }
    }
}
