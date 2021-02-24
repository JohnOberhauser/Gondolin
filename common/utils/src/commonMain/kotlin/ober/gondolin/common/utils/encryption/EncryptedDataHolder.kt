package ober.gondolin.common.utils.encryption

data class EncryptedDataHolder(
    val encryptedData: String,
    val salt: String
)