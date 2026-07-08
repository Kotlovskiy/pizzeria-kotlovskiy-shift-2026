package com.example.network.di

import com.example.network.TokenInterceptor
import com.example.network.TokenManager
import com.example.storage.EncryptedDataStore
import com.example.storage.IDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkCoreModule {
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)

    @Provides
    @Singleton
    fun provideTokenManager(
        @EncryptedDataStore dataStore: IDataStore
    ): TokenManager =
        TokenManager(dataStore)

    @Provides
    fun provideTokenInterceptor(
        tokenManager: TokenManager
    ) : TokenInterceptor =
        TokenInterceptor(tokenManager)

    @Provides
    @Singleton
    fun provideJson(): Json =
        Json {
            ignoreUnknownKeys = true
        }
}
