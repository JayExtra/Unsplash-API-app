package com.dev.james.unsplashapiapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dev.james.unsplashapiapp.Utils.UNSPLASH_REMOTE_KEYS_TABLE

/*
* Will be used by the RemoteMediator class to know which age key to be loaded
* next once the user scrolls to the bottom of the page*/
@Entity(tableName = UNSPLASH_REMOTE_KEYS_TABLE)
data class UnsplashRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id : String ,
    val prevPageKey : Int? ,
    val nextPageKey : Int?
)
