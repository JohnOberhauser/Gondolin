package ober.gondolin.common.database.models.index

import kotlinx.serialization.Serializable

@Serializable
data class Directory(
    val directories: MutableList<Directory>,
    val files: MutableList<File>
)