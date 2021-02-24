package ober.gondolin.common.utils.simpleStorage

import com.russhwolf.settings.Settings
import ober.gondolin.common.utils.encryption.*

interface SimpleStorage {
    fun saveEncryptionKey(encryptionKey: String, pin: String)
    fun getEncryptionKey(pin: String): String
}

class RushSimpleStorage: SimpleStorage {

    private val settings: Settings = Settings()

    override fun saveEncryptionKey(encryptionKey: String, pin: String) {
        val salt = RandomGenerator.generateSalt()
        setEncryptionOptions(key = pin, salt = salt)
        settings.putString(ENCRYPTION_KEY, encrypt(encryptionKey))
        settings.putString(ENCRYPTION_KEY_SALT, salt)
    }

    override fun getEncryptionKey(pin: String): String {
        val eKey = settings.getString(ENCRYPTION_KEY)
        val salt = settings.getString(ENCRYPTION_KEY_SALT)
        if (eKey.isNotBlank() && salt.isNotBlank()) {
            setEncryptionOptions(key = pin, salt = salt)
            return decrypt(eKey)
        }
        return ""
    }

    companion object {
        private const val ENCRYPTION_KEY = "encryption_key"
        private const val ENCRYPTION_KEY_SALT = "encryption_key_salt"
    }
}