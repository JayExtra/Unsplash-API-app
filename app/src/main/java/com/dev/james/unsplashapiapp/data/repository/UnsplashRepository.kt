package com.dev.james.unsplashapiapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dev.james.unsplashapiapp.Utils.ITEMS_PER_PAGE
import com.dev.james.unsplashapiapp.data.local.UnsplashImageDatabase
import com.dev.james.unsplashapiapp.data.model.UnsplashImage
import com.dev.james.unsplashapiapp.data.paging.SearchPagingSource
import com.dev.james.unsplashapiapp.data.paging.UnsplashRemoteMediator
import com.dev.james.unsplashapiapp.data.remote.UnsplashApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class UnsplashRepository @Inject constructor(
    private val unsplashApi: UnsplashApi ,
    private val unsplashDatabase : UnsplashImageDatabase
) {

    fun getAllImages() : Flow<PagingData<UnsplashImage>> {

        val pagingSourceFactory = { unsplashDatabase.unsplashImageDao().getAllImages()}
        return  Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = UnsplashRemoteMediator(
                unsplashApi = unsplashApi ,
                unsplashImageDatabase =  unsplashDatabase
            ) ,
            pagingSourceFactory = pagingSourceFactory
        ).flow

    }

    fun searchImages(query : String) : Flow<PagingData<UnsplashImage>> {
        return Pager(
            config = PagingConfig(pageSize = 10) ,
            pagingSourceFactory = {
                SearchPagingSource(unsplashApi = unsplashApi , query = query)
            }
        ).flow
    }
}