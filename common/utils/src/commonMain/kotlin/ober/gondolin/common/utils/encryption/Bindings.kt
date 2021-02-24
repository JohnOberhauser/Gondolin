package ober.gondolin.common.utils.encryption

expect fun nextSecureInt(until: Int): Int

expect fun randomByteArray(length: Int): ByteArray

expect fun encrypt(data: String): String

expect fun decrypt(data: String): String

expect fun setEncryptionOptions(
    key: String,
    salt: String,
    iv: ByteArray = RandomGenerator.generateIV(),
    iterationCount: Int = 50_000,
    algorithm: String = "AES/CBC/PKCS5Padding",
    keyAlgorithm: String = "AES",
    charset: String = "UTF8",
    digestAlgorithm: String = "SHA1",
    base64Mode: Base64Mode = Base64Mode.Default,
    secureRandomAlgorithm: String = "SHA1PRNG",
    secretKeyType: String = "PBKDF2WithHmacSHA1"
)