package com.example.network.di

import com.example.network.NetworkConstants.APPLICATION_JSON
import com.example.network.NetworkConstants.BASE_URL
import com.example.network.qualifiers.AuthorizedOkHttpClient
import com.example.network.qualifiers.AuthorizedRetrofitClient
import com.example.network.qualifiers.UnauthorizedOkHttpClient
import com.example.network.qualifiers.UnauthorizedRetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitClientsModule {
    @Provides
    @Singleton
    @UnauthorizedRetrofitClient
    fun provideUnauthorizedRetrofitClient(
        @UnauthorizedOkHttpClient okHttpClient: OkHttpClient,
        json: Json
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(APPLICATION_JSON.toMediaType()))
            .build()

    @Provides
    @Singleton
    @AuthorizedRetrofitClient
    fun provideAuthorizedRetrofitClient(
        @AuthorizedOkHttpClient okHttpClient: OkHttpClient,
        json: Json
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(APPLICATION_JSON.toMediaType()))
            .build()
}
