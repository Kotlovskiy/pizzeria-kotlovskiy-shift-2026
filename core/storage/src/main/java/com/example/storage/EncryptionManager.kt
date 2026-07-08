package com.example.storage

import android.content.Context
import com.google.crypto.tink.Aead
import com.google.crypto.tink.KeyTemplate
import com.google.crypto.tink.RegistryConfiguration
import com.google.crypto.tink.aead.AeadConfig
import com.google.crypto.tink.aead.PredefinedAeadParameters
import com.google.crypto.tink.integration.android.AndroidKeysetManager

class EncryptionManager(context: Context) {

    private val aead: Aead

    init {
        AeadConfig.register()

        val keysetHandle = AndroidKeysetManager.Builder()
            .withSharedPref(context, KEYSET_NAME, PREF_FILE_NAME)
            .withKeyTemplate(KeyTemplate.createFrom(PredefinedAeadParameters.AES256_GCM))
            .withMasterKeyUri(MASTER_KEY_URI)
            .build()
            .keysetHandle

        aead = keysetHandle.getPrimitive(
            RegistryConfiguration.get(),
            Aead::class.java
        )
    }

    fun encrypt(plaintext: String, associatedData: ByteArray? = null): String {
        val ciphertext = aead.encrypt(
            plaintext.toByteArray(ENCODING),
            associatedData
        )
        return android.util.Base64.encodeToString(
            ciphertext,
            ENCODING_FLAGS
        )
    }

    fun decrypt(ciphertext: String, associatedData: ByteArray? = null): String {
        val decodedBytes = android.util.Base64.decode(
            ciphertext,
            ENCODING_FLAGS
        )
        val plaintextBytes = aead.decrypt(
            decodedBytes,
            associatedData
        )
        return String(plaintextBytes, ENCODING)
    }

    private companion object {
        const val MASTER_KEY_URI = "android-keystore://master_key"
        const val KEYSET_NAME = "app_keyset"
        const val PREF_FILE_NAME = "app_keyset_prefs"

        val ENCODING = Charsets.UTF_8
        const val ENCODING_FLAGS = android.util.Base64.NO_WRAP
    }
}
