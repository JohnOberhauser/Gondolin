package ober.gondolin.common.utils.encryption

object RandomGenerator {

    private const val CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_+;:<>/?"

    fun generateRandomKey(length: Int): String {
        val keyBuilder = StringBuilder()
        for (i in 0 until length) {
            keyBuilder.append(CHARACTERS[Random.nextSecureInt(CHARACTERS.length)])
        }
        return keyBuilder.toString()
    }
}