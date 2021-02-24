package ober.gondolin.common.utils.encryption

actual fun nextSecureInt(until: Int): Int = throw Exception("Stub")

actual fun encrypt(data: String): String = throw Exception("Stub")

actual fun decrypt(data: String): String = throw Exception("Stub")

actual fun setEncryptionOptions(
    key: String,
    salt: String,
    iv: ByteArray,
    iterationCount: Int,
    algorithm: String,
    charset: String,
    digestAlgorithm: String,
    base64Mode: Base64Mode,
    secureRandomAlgorithm: String,
    secretKeyType: String
) {
    throw Exception("Stub")
}