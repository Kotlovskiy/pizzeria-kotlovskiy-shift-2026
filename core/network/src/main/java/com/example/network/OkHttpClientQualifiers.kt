package com.example.network

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
internal annotation class AuthorizedOkHttpClient

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
internal annotation class UnauthorizedOkHttpClient
