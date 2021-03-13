package ober.gondolin.common.utils.encryption

actual object Encryption {

    actual fun encrypt(data: String, key: String): String = throw Exception("Stub")

    actual fun decrypt(data: String, key: String): String = throw Exception("Stub")

    actual fun getHash(data: String): String = throw Exception("Stub")
}