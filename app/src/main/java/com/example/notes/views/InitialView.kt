package com.example.notes.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notes.viewModels.NotesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun InitialView(navController: NavController, viewModel: NotesViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Anotações",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Black
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("insert") },
                containerColor = Color.White,
                contentColor = Color.Black,
                shape = CircleShape,
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Insert")
            }
        }
    ) {
        InitialContentView(it, navController, viewModel)
    }
}

@Composable
fun InitialContentView(it: PaddingValues, navController: NavController, viewModel: NotesViewModel) {
    val state = viewModel.state

    Column(
        modifier = Modifier
            .padding(it)
            .padding(horizontal = 10.dp)
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(count = 2),
            horizontalArrangement = Arrangement.spacedBy(space = 10.dp),
            content = {
                items(state.notesList) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier
                            .shadow(
                                elevation = 60.dp,
                                spotColor = Color.Cyan,
                            )
                            .padding(top = 10 .dp)
                            .border(
                                width = 1.dp,
                                color = Color.Cyan.copy(alpha = .2f),
                                shape = MaterialTheme.shapes.medium
                            )
                            .weight(1f)
                            .background(
                                color = Color.Black,
                                shape = MaterialTheme.shapes.medium
                            )
                            .clickable {
                                navController.navigate("update/${it.id}/${it.title}/${it.text}")
                            }
                            .padding(all = 10.dp),
                        content = {
                            Text(
                                text = it.title.uppercase(),
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )

                            HorizontalDivider(modifier = Modifier.height(5.dp), color = Color.Cyan.copy(alpha = .6f))

                            Text(
                                text = it.text,
                                modifier = Modifier
                            )

                        }
                    )
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}
