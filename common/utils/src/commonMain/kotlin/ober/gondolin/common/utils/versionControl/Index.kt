package ober.gondolin.common.utils.versionControl

import kotlinx.serialization.Serializable
import ober.gondolin.common.database.models.index.File

@Serializable
data class Index(
    val rootDirectory: File.Directory = File.Directory(
        name = "/",
        path = listOf()
    ),
    val commits: MutableList<Commit> = mutableListOf()
) {

    val allFiles: MutableList<File> = mutableListOf(rootDirectory)

    fun filesWithPath(path: List<String>): List<File> {
        val files = allFiles.filter { it.path == path }
        println("test")
        return files
    }
}