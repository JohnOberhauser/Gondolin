package ober.gondolin.common.utils.simpleStorage

import com.russhwolf.settings.Settings
import ober.gondolin.common.utils.encryption.*

interface SimpleStorage {
    fun saveString(string: String, encryptionKey: String, settingsKey: Key)
    fun getString(encryptionKey: String, settingsKey: Key): String
    fun doesValueExist(key: Key): Boolean

    enum class Key(val value: String) {
        ENCRYPTION_KEY("encryption_key"),
        INDEX("index")
    }
}

class RushSimpleStorage: SimpleStorage {

    private val settings: Settings = Settings()

    override fun saveString(string: String, encryptionKey: String, settingsKey: SimpleStorage.Key) {
        settings.putString(settingsKey.value, Encryption.encrypt(string, encryptionKey))
    }

    override fun getString(encryptionKey: String, settingsKey: SimpleStorage.Key): String {
        val value = settings.getString(settingsKey.value)
        if (value.isNotBlank()) {
            return try {
                Encryption.decrypt(value, encryptionKey)
            } catch (e: Exception) {
                ""
            }
        }
        return ""
    }

    override fun doesValueExist(key: SimpleStorage.Key): Boolean {
        val value = settings.getString(key.value)
        return value.isNotBlank()
    }
}