package com.dev.james.unsplashapiapp.presentation.navigation

sealed class Screen(val route : String) {
    object HomeScreen : Screen("home_screen")
    object SearchScreen : Screen("search_screen")
}
