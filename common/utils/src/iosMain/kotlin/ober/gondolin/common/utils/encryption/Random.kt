package ober.gondolin.common.utils.encryption

internal actual object Random {
    actual fun nextSecureInt(until: Int): Int = throw Exception("Stub")

    actual fun randomByteArray(length: Int): ByteArray = throw Exception("Stub")
}