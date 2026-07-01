package com.example.network

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AuthorizedRetrofitClient

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class UnauthorizedRetrofitClient
