package com.example.notes.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notes.models.Notes
import com.example.notes.viewModels.NotesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun UpdateView(
    navController: NavController,
    viewModel: NotesViewModel,
    id: Int,
    name: String?,
    email: String?,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Atualizar Anotação",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Black
                ),
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        content = {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White,
                            )
                        }
                    )
                }
            )
        },
    ) {
        UpdateContentView(it, navController, viewModel, id, name, email)
    }
}

@Composable
internal fun UpdateContentView(
    it: PaddingValues,
    navController: NavController,
    viewModel: NotesViewModel,
    id: Int,
    title: String?,
    text: String?,
) {
    var mutableTitle by remember { mutableStateOf(title) }
    var mutableText by remember { mutableStateOf(text) }

    Column(
        modifier = Modifier
            .padding(it)
            .padding(top = 30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = mutableTitle ?: "",
            onValueChange = { mutableTitle = it },
            label = { Text(text = "Título") },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.White,
                focusedBorderColor = Color.Cyan
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )

        OutlinedTextField(
            value = mutableText ?: "",
            onValueChange = { mutableText = it },
            label = { Text(text = "Texto") },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.White,
                focusedBorderColor = Color.Cyan
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(space = 30.dp),
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .fillMaxWidth(),
            content = {
                Button(
                    onClick = {
                        val user = Notes(id = id, title = mutableTitle!!, text = mutableText!!)
                        viewModel.deleteNote(user)
                        navController.popBackStack()
                    },
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Cyan
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "DELETAR")
                }

                Button(
                    onClick = {
                        val user = Notes(id = id, title = mutableTitle!!, text = mutableText!!)
                        viewModel.updateNote(note = user)
                        navController.popBackStack()
                    },
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Cyan
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "ATUALIZAR")
                }
            }
        )
    }
}
