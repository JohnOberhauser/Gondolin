package ober.gondolin.common.utils.encryption

expect object Encryption {

    fun encrypt(data: String): String

    fun decrypt(data: String): String

    fun setEncryptionOptions(
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
}