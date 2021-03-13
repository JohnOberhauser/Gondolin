package ober.gondolin.common.database.models.index

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class File {
    abstract val name: String

    @Serializable
    //@SerialName("Credentials")
    data class CredentialsFile(
        override val name: String,
        val website: String? = null,
        val username: String? = null,
        val password: String? = null,
        val notes: String? = null
    ): File()

    @Serializable
    //@SerialName("Image")
    class ImageFile(
        override val name: String,
        val thumbnail: String? = null,
        val image: String? = null
    ): File()

    @Serializable
    //@SerialName("Video")
    class VideoFile(
        override val name: String,
        val thumbnail: String? = null,
        val video: String? = null
    ): File()

    @Serializable
    //@SerialName("Other")
    class OtherFile(
        override val name: String,
        val data: String? = null
    ): File()
}

