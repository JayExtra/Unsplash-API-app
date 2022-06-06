package com.dev.james.unsplashapiapp.di

import com.dev.james.unsplashapiapp.Utils.BASE_URL
import com.dev.james.unsplashapiapp.data.remote.UnsplashApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkhttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15 , TimeUnit.SECONDS)
            .connectTimeout(15 , TimeUnit.SECONDS)
            .build()
    }


    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideRetrofit( okHttpClient: OkHttpClient) : Retrofit {
        val contentType = MediaType.get("application/json")
        val json = Json {
            ignoreUnknownKeys = true //will ignore properties
        // of the response which you do not require or have not included in your response class
        }
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideUnsplashApi(retrofit : Retrofit) : UnsplashApi {
        return retrofit.create(UnsplashApi::class.java)
    }
}