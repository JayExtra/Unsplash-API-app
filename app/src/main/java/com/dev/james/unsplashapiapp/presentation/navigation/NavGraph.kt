package com.dev.james.unsplashapiapp.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.dev.james.unsplashapiapp.presentation.screens.HomeScreen
import com.dev.james.unsplashapiapp.presentation.screens.SearchScreen

@ExperimentalCoilApi
@ExperimentalPagingApi
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ){

        composable(route = Screen.HomeScreen.route){
            HomeScreen(navController = navController)
        }
        composable(route= Screen.SearchScreen.route){
            SearchScreen(navController = navController)
        }

    }
}