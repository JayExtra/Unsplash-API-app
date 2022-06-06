package com.dev.james.unsplashapiapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dev.james.unsplashapiapp.data.model.UnsplashImage
import com.dev.james.unsplashapiapp.data.model.UnsplashRemoteKeys
import com.dev.james.unsplashapiapp.data.local.dao.UnsplashImageDao
import com.dev.james.unsplashapiapp.data.local.dao.UnsplashRemoteKeysDao

@Database(entities = [UnsplashImage::class , UnsplashRemoteKeys::class] , version = 3)
abstract class UnsplashImageDatabase : RoomDatabase() {
    abstract fun unsplashImageDao() : UnsplashImageDao
    abstract fun unsplashRemoteKeysDao() : UnsplashRemoteKeysDao
}