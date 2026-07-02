package com.example.network

import com.example.network.NetworkConstants.APPLICATION_JSON
import com.example.network.NetworkConstants.BASE_URL
import com.example.network.api.AuthApi
import com.example.network.api.OrderApi
import com.example.network.api.PizzaApi
import com.example.network.api.UserApi
import com.example.storage.EncryptedDataStore
import com.example.storage.IDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

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

    @Provides
    @Singleton
    @UnauthorizedOkHttpClient
    fun provideUnauthorizedOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

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
    @AuthorizedOkHttpClient
    fun provideAuthorizedOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        tokenInterceptor: TokenInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(tokenInterceptor)
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

    @Provides
    fun provideAuthApi(
        @UnauthorizedRetrofitClient client: Retrofit
    ): AuthApi = client.create<AuthApi>()

    @Provides
    fun provideOrderApi(
        @AuthorizedRetrofitClient client: Retrofit
    ): OrderApi = client.create<OrderApi>()

    @Provides
    fun providePizzaApi(
        @UnauthorizedRetrofitClient client: Retrofit
    ): PizzaApi = client.create<PizzaApi>()

    @Provides
    fun provideUserApi(
        @AuthorizedRetrofitClient client: Retrofit
    ): UserApi = client.create<UserApi>()
}
