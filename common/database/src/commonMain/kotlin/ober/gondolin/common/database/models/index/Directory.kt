package ober.gondolin.common.database.models.index

data class Directory(
    val directories: MutableList<Directory>,
    val files: MutableList<File>
)