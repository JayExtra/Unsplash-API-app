package com.dev.james.unsplashapiapp.presentation.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.dev.james.unsplashapiapp.presentation.viewmodel.SearchViewModel

@OptIn(ExperimentalCoilApi::class)
@Composable
fun SearchScreen(
    navController : NavHostController ,
    searchViewModel: SearchViewModel = hiltViewModel()) {
    val searchQuery by searchViewModel.searchQuery
    val searchedImages = searchViewModel.searchedImages.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchWidget(
                text = searchQuery,
                onTextChange = {
                    searchViewModel.updateSearchQuery(query = it)
                },
                onSearchClicked = {
                    searchViewModel.searchHeroes(query = it)
                },
                onCloseClicked = {
                    navController.popBackStack()
                }
            )
        },
        content = {
            ImageListContent(items = searchedImages)
        }
    )
}