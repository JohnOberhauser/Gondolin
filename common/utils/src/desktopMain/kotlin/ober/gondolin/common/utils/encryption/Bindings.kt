package ober.gondolin.common.utils.encryption

import java.security.SecureRandom

actual fun nextSecureInt(until: Int): Int = SecureRandom().nextInt(until)