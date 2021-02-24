package ober.gondolin.common.utils.encryption

import se.simbio.encryption.Encryption
import third.part.android.util.Base64
import java.security.SecureRandom

actual fun nextSecureInt(until: Int): Int {
    PRNGFixes.apply()
    return SecureRandom().nextInt(until)
}

actual fun encrypt(data: String): String {
    return EncryptionOptions.encryption?.encrypt(data) ?: throw EncryptionOptionsNotSetException()
}

actual fun decrypt(data: String): String {
    return EncryptionOptions.encryption?.decrypt(data) ?: throw EncryptionOptionsNotSetException()
}

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
    PRNGFixes.apply()
    EncryptionOptions.encryption = Encryption.Builder()
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
        .build()
}