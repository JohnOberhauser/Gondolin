package ober.gondolin.common.utils.encryption

import java.security.SecureRandom

internal actual object Random {
    actual fun nextSecureInt(until: Int): Int {
        PRNGFixes.apply()
        return SecureRandom().nextInt(until)
    }

    actual fun randomByteArray(length: Int): ByteArray {
        PRNGFixes.apply()
        val byteArray = ByteArray(length)
        SecureRandom().nextBytes(byteArray)
        return byteArray
    }
}