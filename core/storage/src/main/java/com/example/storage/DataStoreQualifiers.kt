package com.example.storage

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class PlainDataStore

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class EncryptedDataStore
