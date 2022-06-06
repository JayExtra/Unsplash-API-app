package com.dev.james.unsplashapiapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.dev.james.unsplashapiapp.data.repository.UnsplashRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: UnsplashRepository
) : ViewModel() {
    val getAllImages = repository.getAllImages()
}