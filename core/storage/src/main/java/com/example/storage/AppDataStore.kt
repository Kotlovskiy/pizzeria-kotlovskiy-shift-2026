package com.example.storage

import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory.create
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class AppDataStore(context: Context) : IDataStore {

    private val dataStore = create {
        context.preferencesDataStoreFile(DATA_STORE_NAME)
    }

    override suspend fun getString(key: String): String? {
        return dataStore.data.firstOrNull()?.let { it[getPrefKey(key)] }
    }

    override suspend fun setString(key: String, value: String) {
        dataStore.edit {
            it[getPrefKey(key)] = value
        }
    }

    override suspend fun clearString(key: String) {
        dataStore.edit {
            it.remove(getPrefKey(key))
        }
    }

    override fun observeString(key: String): Flow<String?> {
        return dataStore.data.map { it[getPrefKey(key)] }
    }

    private fun getPrefKey(key: String): Preferences.Key<String> {
        return stringPreferencesKey(key)
    }

    companion object {
        private const val DATA_STORE_NAME = "datastore"
    }
}
