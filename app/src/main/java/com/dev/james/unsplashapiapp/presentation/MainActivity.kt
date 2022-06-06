package com.dev.james.unsplashapiapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.dev.james.unsplashapiapp.presentation.navigation.SetupNavGraph
import com.dev.james.unsplashapiapp.ui.theme.UnsplashApiAppTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalPagingApi::class)
@ExperimentalPagingApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalCoilApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnsplashApiAppTheme {
               val navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}
