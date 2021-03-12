package ober.gondolin.common.utils.encryption

import kotlin.test.Test
import kotlin.test.assertTrue

class EncryptionTest {

    @Test
    fun testEncryption() {
        val key = "test_key"
        val data = "test_data"

        val encryptedData = Encryption.encrypt(data, key)
        val decryptedData = Encryption.decrypt(encryptedData, key)
        assertTrue { decryptedData == data }
    }
}