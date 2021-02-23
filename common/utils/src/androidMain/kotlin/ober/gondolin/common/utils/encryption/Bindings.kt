package ober.gondolin.common.utils.encryption

import java.security.SecureRandom

actual fun nextSecureInt(until: Int): Int {
    PRNGFixes.apply()
    return SecureRandom().nextInt(until)
}