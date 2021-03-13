package ober.gondolin.common.utils.encryption

import java.security.MessageDigest
import java.util.*

actual object Encryption {

    actual fun encrypt(data: String, key: String): String = Crypt.encrypt(data, key)

    actual fun decrypt(data: String, key: String): String = Crypt.decrypt(data, key)

    actual fun getHash(data: String): String {
        MessageDigest.getInstance("SHA-1").apply {
            reset()
            update(data.toByteArray())
            return Base64.getEncoder().encodeToString(digest())
        }
    }
}