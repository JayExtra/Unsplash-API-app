package com.dev.james.unsplashapiapp.presentation.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.dev.james.unsplashapiapp.presentation.navigation.Screen
import com.dev.james.unsplashapiapp.presentation.viewmodel.HomeViewModel

@OptIn(ExperimentalCoilApi::class)
@Composable
fun HomeScreen(
    navController : NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
){

    val getAllImages = homeViewModel.getAllImages.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeScreenTopBar (
                onSearchClicked = {

                navController.navigate(Screen.SearchScreen.route)

                }
            )
        },
        content = {
            ImageListContent(items = getAllImages)
        }
    )

}