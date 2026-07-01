package com.example.network

import com.example.storage.IDataStore

class TokenManager(
    private val storage: IDataStore
) {
    suspend fun getToken(): String? {
        return storage.getString(TOKEN_KEY)
    }

    suspend fun setToken(newToken: String) {
        storage.setString(key = TOKEN_KEY, value = newToken)
    }

    companion object {
        const val TOKEN_KEY = "token"
    }
}
