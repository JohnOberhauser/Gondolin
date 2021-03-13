package ober.gondolin.common.database.models.index

import kotlinx.serialization.Serializable

@Serializable
data class Directory(
    val name: String,
    val directories: MutableList<Directory> = mutableListOf(),
    val files: MutableList<File> = mutableListOf()
) {

    fun findFilesDirectory(file: File): Directory? {
        if (files.contains(file)) {
            return this
        }

        directories.forEach { directory ->
            directory.findFilesDirectory(file)?.let {
                return it
            }
        }

        return null
    }

    fun findContainingDirectory(target: Directory): Directory? {
        if (directories.contains(target)) {
            return this
        }

        directories.forEach { directory ->
            directory.findContainingDirectory(target)?.let {
                return it
            }
        }

        return null
    }
}