package ober.gondolin.common.utils.encryption

actual object Encryption {

    actual fun encrypt(data: String, key: String): String = Crypt.encrypt(data, key)

    actual fun decrypt(data: String, key: String): String = Crypt.decrypt(data, key)
}