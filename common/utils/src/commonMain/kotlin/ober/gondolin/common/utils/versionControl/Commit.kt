package ober.gondolin.common.utils.versionControl

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ober.gondolin.common.database.models.index.Directory
import ober.gondolin.common.database.models.index.File
import ober.gondolin.common.utils.encryption.Encryption

@Serializable
data class Commit(
    val operation: Operation,
    val previousHash: String
) {
    val hash: String = Encryption.getHash(previousHash + Json.encodeToString(operation))
}

@Serializable
sealed class Operation {
    @Serializable
    data class AddFile(val file: File, val toDirectory: Directory): Operation()
    @Serializable
    data class DeleteFile(val file: File): Operation()

    @Serializable
    data class AddDirectory(val directory: Directory, val toDirectory: Directory): Operation()
    @Serializable
    data class DeleteDirectory(val directory: Directory): Operation()
}