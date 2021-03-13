package ober.gondolin.common.utils.versionControl

import kotlinx.serialization.Serializable
import ober.gondolin.common.database.models.index.Directory

@Serializable
data class Index(
    val rootDirectory: Directory = Directory(
        name = "/"
    ),
    val commits: MutableList<Commit> = mutableListOf()
)