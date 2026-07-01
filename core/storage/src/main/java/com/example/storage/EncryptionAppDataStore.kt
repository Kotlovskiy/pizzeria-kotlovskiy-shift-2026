package com.example.storage

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EncryptionAppDataStore(
    private val appDataStore: AppDataStore,
    private val encryptionManager: EncryptionManager
) : IDataStore {
    override suspend fun getString(key: String): String? {
        return appDataStore.getString(key)?.let { encrypted ->
            encryptionManager.decrypt(encrypted)
        }
    }

    override suspend fun setString(key: String, value: String) {
        appDataStore.setString(key, encryptionManager.encrypt(value))
    }

    override suspend fun clearString(key: String) {
        appDataStore.clearString(key)
    }

    override fun observeString(key: String): Flow<String?> {
        return appDataStore.observeString(key).map { encrypted ->
            encrypted?.let { encryptionManager.decrypt(it) }
        }
    }
}
