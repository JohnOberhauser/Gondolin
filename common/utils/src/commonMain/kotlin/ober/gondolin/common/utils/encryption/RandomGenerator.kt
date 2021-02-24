package ober.gondolin.common.utils.encryption

object RandomGenerator {

    private const val CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_+;:<>/?"

    fun generateRandomKey(length: Int): String {
        val keyBuilder = StringBuilder()
        for (i in 0 until length) {
            keyBuilder.append(CHARACTERS[nextSecureInt(CHARACTERS.length)])
        }
        return keyBuilder.toString()
    }

    fun generateIV(): ByteArray = randomByteArray(16)

    /**
     * @param keyLength should be the same as the key length in your encryption algorithm.  I.E for
     * AES256, use a key length of 256.
     */
    fun generateSalt(keyLength: Int = 256): String = randomByteArray(keyLength).decodeToString()
}