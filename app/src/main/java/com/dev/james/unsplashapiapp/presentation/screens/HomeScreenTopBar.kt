package com.dev.james.unsplashapiapp.presentation.screens


import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dev.james.unsplashapiapp.ui.theme.topAppBarBackgroundColor
import com.dev.james.unsplashapiapp.ui.theme.topAppBarContentColor


@Composable
fun HomeScreenTopBar(
    onSearchClicked : () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Unsplash Api App",
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            IconButton(onClick = onSearchClicked) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon"
                )
            }
        }
    )
}

@Composable
@Preview
fun HomeTopBarPreview() {
    HomeScreenTopBar {}
}