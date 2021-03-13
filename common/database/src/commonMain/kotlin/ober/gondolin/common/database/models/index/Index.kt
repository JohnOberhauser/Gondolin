package ober.gondolin.common.database.models.index

import kotlinx.serialization.Serializable

@Serializable
data class Index(
    val rootDirectory: Directory = Directory(
        name = "/"
    )
)