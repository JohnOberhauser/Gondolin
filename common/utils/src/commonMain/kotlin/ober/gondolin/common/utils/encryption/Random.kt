package ober.gondolin.common.utils.encryption

internal expect object Random {
    fun nextSecureInt(until: Int): Int

    fun randomByteArray(length: Int): ByteArray
}