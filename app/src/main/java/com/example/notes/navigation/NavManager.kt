package com.example.notes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notes.viewModels.NotesViewModel
import com.example.notes.views.InitialView
import com.example.notes.views.InsertView
import com.example.notes.views.UpdateView

@Composable
internal fun NavManager(viewModel: NotesViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "initial",
    ) {
        composable(route = "initial") {
            InitialView(navController, viewModel)
        }

        composable(route = "insert") {
            InsertView(navController, viewModel)
        }

        composable(
            route = "update/{id}/{title}/{text}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType },
                navArgument("title") { type = NavType.StringType },
                navArgument("text") { type = NavType.StringType },
            )
            ) {
            UpdateView(
                navController,
                viewModel,
                it.arguments!!.getInt("id"),
                it.arguments!!.getString("title"),
                it.arguments!!.getString("text"),
            )
        }
    }
}
