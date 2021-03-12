package ober.gondolin.common.utils.encryption

import java.security.SecureRandom

internal actual object Random {
    actual fun nextSecureInt(until: Int): Int {
        return SecureRandom().nextInt(until)
    }

    actual fun randomByteArray(length: Int): ByteArray {
        val byteArray = ByteArray(length)
        SecureRandom().nextBytes(byteArray)
        return byteArray
    }
}