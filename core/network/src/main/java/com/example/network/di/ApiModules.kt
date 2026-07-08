package com.example.network.di

import com.example.network.api.AuthApi
import com.example.network.api.OrderApi
import com.example.network.api.PaymentApi
import com.example.network.api.PizzaApi
import com.example.network.api.UserApi
import com.example.network.qualifiers.AuthorizedRetrofitClient
import com.example.network.qualifiers.UnauthorizedRetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object ApiModules {
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
    fun providePaymentApi(
        @UnauthorizedRetrofitClient client: Retrofit
    ): PaymentApi = client.create<PaymentApi>()

    @Provides
    fun provideUserApi(
        @AuthorizedRetrofitClient client: Retrofit
    ): UserApi = client.create<UserApi>()
}
