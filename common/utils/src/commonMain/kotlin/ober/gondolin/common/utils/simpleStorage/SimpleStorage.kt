package ober.gondolin.common.utils.simpleStorage

import com.russhwolf.settings.Settings
import ober.gondolin.common.utils.encryption.*

interface SimpleStorage {
    fun saveEncryptionKey(encryptionKey: String, pin: String)

    /**
     * returns encryption key or an empty string if it doesn't exist or the pin was wrong
     */
    fun getEncryptionKey(pin: String): String
    val hasSavedEncryptionKey: Boolean
}

class RushSimpleStorage: SimpleStorage {

    private val settings: Settings = Settings()

    override fun saveEncryptionKey(encryptionKey: String, pin: String) {
        val salt = RandomGenerator.generateSalt()
        Encryption.setEncryptionOptions(key = pin, salt = salt)
        settings.putString(ENCRYPTION_KEY, Encryption.encrypt(encryptionKey))
        settings.putString(ENCRYPTION_KEY_SALT, salt)
    }

    override fun getEncryptionKey(pin: String): String {
        val eKey = settings.getString(ENCRYPTION_KEY)
        val salt = settings.getString(ENCRYPTION_KEY_SALT)
        if (eKey.isNotBlank() && salt.isNotBlank()) {
            Encryption.setEncryptionOptions(key = pin, salt = salt)
            return try {
                Encryption.decrypt(eKey)
            } catch (e: Exception) {
                ""
            }
        }
        return ""
    }

    override val hasSavedEncryptionKey: Boolean
        get() {
            val eKey = settings.getString(ENCRYPTION_KEY)
            val salt = settings.getString(ENCRYPTION_KEY_SALT)
            return eKey.isNotBlank() && salt.isNotBlank()
        }

    companion object {
        private const val ENCRYPTION_KEY = "encryption_key"
        private const val ENCRYPTION_KEY_SALT = "encryption_key_salt"
    }
}