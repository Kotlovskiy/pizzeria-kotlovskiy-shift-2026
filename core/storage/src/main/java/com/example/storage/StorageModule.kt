package com.example.storage

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class StorageModule {

    @Binds
    @Singleton
    @PlainDataStore
    abstract fun bindPlainDataStore(appDataStore: AppDataStore): IDataStore

    @Binds
    @Singleton
    @EncryptedDataStore
    abstract fun bindEncryptedDataStore(
        encryptionAppDataStore: EncryptionAppDataStore
    ): IDataStore

    companion object {
        @Provides
        @Singleton
        fun provideEncryptionManager(@ApplicationContext context: Context) =
            EncryptionManager(context)

        @Provides
        @Singleton
        fun provideAppDataStore(@ApplicationContext context: Context): AppDataStore =
            AppDataStore(context)

        @Provides
        @Singleton
        fun provideEncryptionAppDataStore(
            appDataStore: AppDataStore,
            encryptionManager: EncryptionManager
        ): EncryptionAppDataStore =
            EncryptionAppDataStore(appDataStore, encryptionManager)
    }
}
