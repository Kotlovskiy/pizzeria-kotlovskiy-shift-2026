package com.example.storage

import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory.create
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.concurrent.ConcurrentHashMap

class EncryptionAppDataStore(
    context: Context,
    private val encryptionManager: EncryptionManager
) {
    private val dataStore = create {
        context.preferencesDataStoreFile(DATA_STORE_NAME)
    }

    suspend fun getString(key: String): String {
        return withContext(Dispatchers.IO) {
            encryptionManager.decrypt(dataStore.data.first()[getPrefKey(key)] ?: "")
        }
    }

    suspend fun setString(key: String, value: String) {
        return withContext(Dispatchers.IO) {
            dataStore.edit {
                it[getPrefKey(key)] = encryptionManager.encrypt(value)
            }
        }
    }

    suspend fun clearString(key: String) {
        withContext(Dispatchers.IO) {
            dataStore.edit {
                it.remove(getPrefKey(key))
            }
        }
    }

    fun observeString(key: String): Flow<String?> {
        return dataStore.data.map {
            encryptionManager.decrypt(it[getPrefKey(key)] ?: "")
        }
    }

    private fun getPrefKey(key: String): Preferences.Key<String> {
        return keys.getOrPut(key) { stringPreferencesKey(key) }
    }

    companion object {
        private const val DATA_STORE_NAME = "encrypted_datastore"
        private val keys = ConcurrentHashMap<String, Preferences.Key<String>>()
    }
}
