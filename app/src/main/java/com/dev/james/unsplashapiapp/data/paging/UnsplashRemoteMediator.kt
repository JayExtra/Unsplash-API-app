package com.dev.james.unsplashapiapp.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.dev.james.unsplashapiapp.Utils.ITEMS_PER_PAGE
import com.dev.james.unsplashapiapp.data.local.UnsplashImageDatabase
import com.dev.james.unsplashapiapp.data.model.UnsplashImage
import com.dev.james.unsplashapiapp.data.model.UnsplashRemoteKeys
import com.dev.james.unsplashapiapp.data.remote.UnsplashApi
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class UnsplashRemoteMediator(
    private val unsplashApi: UnsplashApi ,
    private val unsplashImageDatabase: UnsplashImageDatabase
) : RemoteMediator<Int , UnsplashImage>() {

    private val unsplashImageDao = unsplashImageDatabase.unsplashImageDao()
    private val unsplashRemoteKeysDao = unsplashImageDatabase.unsplashRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UnsplashImage>
    ): MediatorResult {
        return try {
            //here we are calculating the value of the current page we are in so that
            // we can know if we are loading pages for the first time and populate the
            // database or are we adding new items to the end of the list or at the
            // start. This is all based on the load type that is happening, if is REFRESH it means we are
            // triggering a new load and the db is empty. If it is PREPEND we are trying to populate at the
            // start and if it is APPEND it means we are at the end of the list and we need to load new items and
            // add to the end.
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPageKey?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPageKey
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPageKey
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = unsplashApi.getAllImages(page = currentPage, perPage = ITEMS_PER_PAGE)
            val endOfPaginationReached = response.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            unsplashImageDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    unsplashImageDao.deleteAllImages()
                    unsplashRemoteKeysDao.deleteAllRemoteKeys()
                }
                val keys = response.map { unsplashImage ->
                    UnsplashRemoteKeys(
                        id = unsplashImage.id,
                        prevPageKey = prevPage,
                        nextPageKey = nextPage
                    )
                }
                unsplashRemoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                unsplashImageDao.addImages(images = response)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }


    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, UnsplashImage>
    ): UnsplashRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                unsplashRemoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, UnsplashImage>
    ): UnsplashRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { unsplashImage ->
                unsplashRemoteKeysDao.getRemoteKeys(id = unsplashImage.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, UnsplashImage>
    ): UnsplashRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { unsplashImage ->
                unsplashRemoteKeysDao.getRemoteKeys(id = unsplashImage.id)
            }
    }
}