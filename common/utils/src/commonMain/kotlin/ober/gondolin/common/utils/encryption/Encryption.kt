package ober.gondolin.common.utils.encryption

expect object Encryption {

    fun encrypt(data: String, key: String): String

    fun decrypt(data: String, key: String): String

    fun getHash(data: String): String
}