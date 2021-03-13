package ober.gondolin.common.utils.versionControl

import kotlinx.serialization.Serializable
import ober.gondolin.common.database.models.index.Directory
import ober.gondolin.common.database.models.index.File

@Serializable
data class Commit(
    val operation: Operation,
    var completed: Boolean = false
)

@Serializable
sealed class Operation {
    data class AddFile(val file: File, val toDirectory: Directory): Operation()
    data class DeleteFile(val file: File): Operation()

    data class AddDirectory(val directory: Directory, val toDirectory: Directory): Operation()
    data class DeleteDirectory(val directory: Directory): Operation()
}