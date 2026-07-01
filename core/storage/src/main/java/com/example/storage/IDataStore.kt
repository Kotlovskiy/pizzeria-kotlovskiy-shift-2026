package com.example.storage

import kotlinx.coroutines.flow.Flow

interface IDataStore {
    suspend fun getString(key: String): String?
    suspend fun setString(key: String, value: String)
    suspend fun clearString(key: String)
    fun observeString(key: String): Flow<String?>
}
