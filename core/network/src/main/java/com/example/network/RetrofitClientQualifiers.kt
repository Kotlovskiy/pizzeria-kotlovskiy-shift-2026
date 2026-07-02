package com.example.network

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
internal annotation class AuthorizedRetrofitClient

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
internal annotation class UnauthorizedRetrofitClient
