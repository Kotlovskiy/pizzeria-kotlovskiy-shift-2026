package com.example.network.qualifiers

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
internal annotation class AuthorizedOkHttpClient

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
internal annotation class UnauthorizedOkHttpClient
