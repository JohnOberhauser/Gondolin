package ober.gondolin.common.database.models.index

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class File {
    abstract val name: String
    abstract var path: List<String>?

    val fullPath: List<String>?
        get() = path?.let { path ->
            mutableListOf<String>().apply {
                addAll(path)
                add(name)
            }.toList()
        }


    @Serializable
    @SerialName("Credentials")
    data class CredentialsFile(
        override val name: String,
        override var path: List<String>? = null,
        val website: String? = null,
        val username: String? = null,
        val password: String? = null,
        val notes: String? = null
    ): File()

    @Serializable
    @SerialName("Image")
    class ImageFile(
        override val name: String,
        override var path: List<String>? = null,
        val thumbnail: String? = null,
        val image: String? = null
    ): File()

    @Serializable
    @SerialName("Video")
    class VideoFile(
        override val name: String,
        override var path: List<String>? = null,
        val thumbnail: String? = null,
        val video: String? = null
    ): File()

    @Serializable
    @SerialName("Other")
    class OtherFile(
        override val name: String,
        override var path: List<String>? = null,
        val data: String? = null
    ): File()

    @Serializable
    @SerialName("Directory")
    data class Directory(
        override val name: String,
        override var path: List<String>? = null,
        val files: MutableList<String> = mutableListOf()
    ): File()
}

