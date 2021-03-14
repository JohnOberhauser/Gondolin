package ober.gondolin.common.utils.versionControl

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ober.gondolin.common.database.models.index.File
import ober.gondolin.common.utils.encryption.Encryption

@Serializable
data class Commit(
    val operation: Operation,
    val previousHash: String
) {
    val hash: String by lazy {
        Encryption.getHash(previousHash + Json.encodeToString(operation))
    }
}

@Serializable
sealed class Operation {
    @Serializable
    data class AddFile(val file: File, val toDirectory: File.Directory): Operation()
    @Serializable
    data class DeleteFile(val file: File, val fromDirectory: File.Directory): Operation()
}