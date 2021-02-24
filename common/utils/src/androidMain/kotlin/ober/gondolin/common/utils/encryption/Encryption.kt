package ober.gondolin.common.utils.encryption

import se.simbio.encryption.Encryption

actual object Encryption {

    private var encryption: Encryption? = null

    actual fun encrypt(data: String): String {
        return encryption?.encrypt(data) ?: throw EncryptionOptionsNotSetException()
    }

    actual fun decrypt(data: String): String {
        return encryption?.decrypt(data) ?: throw EncryptionOptionsNotSetException()
    }

    actual fun setEncryptionOptions(
        key: String,
        salt: String,
        iv: ByteArray,
        iterationCount: Int,
        algorithm: String,
        keyAlgorithm: String,
        charset: String,
        digestAlgorithm: String,
        base64Mode: Base64Mode,
        secureRandomAlgorithm: String,
        secretKeyType: String
    ) {
        PRNGFixes.apply()
        encryption = Encryption.Builder()
            .setKeyLength(256)
            .setKey(key)
            .setSalt(salt)
            .setIv(iv)
            .setIterationCount(iterationCount)
            .setAlgorithm(algorithm)
            .setCharsetName(charset)
            .setDigestAlgorithm(digestAlgorithm)
            .setBase64Mode(base64Mode.value)
            .setSecureRandomAlgorithm(secureRandomAlgorithm)
            .setSecretKeyType(secretKeyType)
            .setKeyAlgorithm(keyAlgorithm)
            .build()
    }
}