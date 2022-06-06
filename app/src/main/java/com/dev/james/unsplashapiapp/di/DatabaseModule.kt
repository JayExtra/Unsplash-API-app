package com.dev.james.unsplashapiapp.di

import android.content.Context
import androidx.room.Room
import com.dev.james.unsplashapiapp.Utils.UNSPLASH_DATABASE
import com.dev.james.unsplashapiapp.data.local.UnsplashImageDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) : UnsplashImageDatabase {
        return Room.databaseBuilder(
            context ,
            UnsplashImageDatabase::class.java,
            UNSPLASH_DATABASE
        ).fallbackToDestructiveMigration()
            .build()
    }

}