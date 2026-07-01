package com.example.storage

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EncryptionAppDataStore(
    private val appDataStore: AppDataStore,
    private val encryptionManager: EncryptionManager
) {
    suspend fun getString(key: String): String? {
        return appDataStore.getString(key)?.let { encrypted ->
            encryptionManager.decrypt(encrypted)
        }
    }

    suspend fun setString(key: String, value: String) {
        appDataStore.setString(key, encryptionManager.encrypt(value))
    }

    suspend fun clearString(key: String) {
        appDataStore.clearString(key)
    }

    fun observeString(key: String): Flow<String?> {
        return appDataStore.observeString(key).map { encrypted ->
            encrypted?.let { encryptionManager.decrypt(it) }
        }
    }
}
