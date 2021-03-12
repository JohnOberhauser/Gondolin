package ober.gondolin.common.utils.encryption

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.nio.charset.Charset
import java.security.GeneralSecurityException
import java.util.*
import javax.crypto.Cipher
import javax.crypto.Mac
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

object Crypt {

    private const val CIPHER_TRANSFORMATION = "AES/CBC/PKCS5Padding"
    private const val CIPHER = "AES"
    private const val AES_KEY_LENGTH_BITS = 256
    private const val IV_LENGTH_BYTES = 16
    private const val PBE_ITERATION_COUNT = 50_000
    private const val PBE_ALGORITHM = "PBKDF2WithHmacSHA512"
    private const val HMAC_ALGORITHM = "HmacSHA256"
    private const val HMAC_KEY_LENGTH_BITS = 256

    fun encrypt(data: String, key: String, iterationCount: Int = PBE_ITERATION_COUNT): String {
        val salt = generateSalt()
        val realKey = generateKeyFromString(key, salt, iterationCount)
        val plainText = data.toByteArray()

        val cipher = Cipher.getInstance(CIPHER_TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, realKey.confidentialityKey, IvParameterSpec(generateIV()))
        val byteCipherText = cipher.doFinal(plainText)
        val ivCipherConcat = cipher.iv.plus(byteCipherText)
        val integrityMac = generateMac(ivCipherConcat, realKey.integrityKey)

        return Json.encodeToString(
            EncryptedData(
                Base64.getEncoder().encodeToString(byteCipherText),
                Base64.getEncoder().encodeToString(cipher.iv),
                Base64.getEncoder().encodeToString(integrityMac),
                Base64.getEncoder().encodeToString(salt),
                iterationCount
            )
        )
    }

    fun decrypt(encryptedDataString: String, key: String): String {
        val encryptedDataObject = Json.decodeFromString<EncryptedData>(encryptedDataString)
        val iv = Base64.getDecoder().decode(encryptedDataObject.iv)
        val cipherText = Base64.getDecoder().decode(encryptedDataObject.cipherText)
        val mac = Base64.getDecoder().decode(encryptedDataObject.mac)
        val salt = Base64.getDecoder().decode(encryptedDataObject.salt)

        val realKey = generateKeyFromString(key, salt, encryptedDataObject.iterations)

        val ivCipherConcat = iv.plus(cipherText)
        val computedMac = generateMac(ivCipherConcat, realKey.integrityKey)
        if (constantTimeEquals(computedMac, mac)) {
            val cipher = Cipher.getInstance(CIPHER_TRANSFORMATION)
            cipher.init(Cipher.DECRYPT_MODE, realKey.confidentialityKey, IvParameterSpec(iv))
            return cipher.doFinal(cipherText).toString(Charset.defaultCharset())
        } else {
            throw GeneralSecurityException("Stored MAC doesn't match computed MAC")
        }
    }

    private fun generateSalt(keyLength: Int = AES_KEY_LENGTH_BITS): ByteArray = Random.randomByteArray(keyLength)

    private fun generateIV(): ByteArray = Random.randomByteArray(IV_LENGTH_BYTES)

    private fun generateKeyFromString(
        string: String,
        salt: ByteArray,
        iterationCount: Int
    ): SecretKeys {
        val keySpec = PBEKeySpec(
            string.toCharArray(),
            salt,
            iterationCount,
            AES_KEY_LENGTH_BITS + HMAC_KEY_LENGTH_BITS
        )
        val secretKeyFactory = SecretKeyFactory.getInstance(PBE_ALGORITHM)
        val keyBytes = secretKeyFactory.generateSecret(keySpec).encoded

        val confidentialityKeyByte = keyBytes.copyOfRange(
            fromIndex = 0,
            toIndex = AES_KEY_LENGTH_BITS / 8
        )

        val integrityKeyBytes = keyBytes.copyOfRange(
            fromIndex = AES_KEY_LENGTH_BITS / 8,
            toIndex = AES_KEY_LENGTH_BITS / 8 + HMAC_KEY_LENGTH_BITS / 8
        )

        return SecretKeys(
            SecretKeySpec(confidentialityKeyByte, CIPHER),
            SecretKeySpec(integrityKeyBytes, HMAC_ALGORITHM)
        )
    }

    private fun generateMac(byteCipherText: ByteArray, integrityKey: SecretKey): ByteArray {
        val mac = Mac.getInstance(HMAC_ALGORITHM)
        mac.init(integrityKey)
        return mac.doFinal(byteCipherText)
    }

    /**
     * an equals function that will always take the exact same amount of time to complete
     */
    private fun constantTimeEquals(a: ByteArray, b: ByteArray): Boolean {
        if (a.size != b.size) {
            return false
        }

        var result = 0
        for (i in a.indices) {
            result += if (a[i] != b[i]) 1 else 0
        }
        return result == 0
    }

    data class SecretKeys(
        val confidentialityKey: SecretKey,
        val integrityKey: SecretKey
    )

    @Serializable
    data class EncryptedData(
        val cipherText: String,
        val iv: String,
        val mac: String,
        val salt: String,
        val iterations: Int
    )
}