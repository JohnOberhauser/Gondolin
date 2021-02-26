package ober.gondolin.common.utils.simpleStorage

import com.russhwolf.settings.Settings
import ober.gondolin.common.utils.encryption.*

interface SimpleStorage {
    fun saveString(string: String, encryptionKey: String, key: Key)
    fun getString(encryptionKey: String, key: Key): String
    fun doesValueExist(key: Key): Boolean

    enum class Key(val value: String) {
        ENCRYPTION_KEY("encryption_key"),
        INDEX("index")
    }
}

class RushSimpleStorage: SimpleStorage {

    private val settings: Settings = Settings()

    override fun saveString(string: String, encryptionKey: String, key: SimpleStorage.Key) {
        val salt = RandomGenerator.generateSalt()
        Encryption.setEncryptionOptions(key = encryptionKey, salt = salt)
        settings.putString(key.value, Encryption.encrypt(string))
        settings.putString(key.value + SALT, salt)
    }

    override fun getString(encryptionKey: String, key: SimpleStorage.Key): String {
        val value = settings.getString(key.value)
        val salt = settings.getString(key.value + SALT)
        if (value.isNotBlank() && salt.isNotBlank()) {
            Encryption.setEncryptionOptions(key = encryptionKey, salt = salt)
            return try {
                Encryption.decrypt(value)
            } catch (e: Exception) {
                ""
            }
        }
        return ""
    }

    override fun doesValueExist(key: SimpleStorage.Key): Boolean {
        val value = settings.getString(key.value)
        val salt = settings.getString(key.value + SALT)
        return value.isNotBlank() && salt.isNotBlank()
    }

    companion object {
        private const val SALT = "_salt"
    }
}