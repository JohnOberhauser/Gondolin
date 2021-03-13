package ober.gondolin.common.utils.versionControl

import ober.gondolin.common.database.models.index.*

fun Index.addCommit(commit: Commit) {
    addCommits(listOf(commit))
}

fun Index.addCommits(commits: List<Commit>) {
    commits.forEach { commit ->
        when(val operation = commit.operation) {
            is Operation.AddFile -> {
                operation.toDirectory.files.firstOrNull { it.name == operation.file.name }?.let {
                    throw FileAlreadyExistsException()
                }
                operation.toDirectory.files.add(operation.file)
            }
            is Operation.DeleteFile -> {
                rootDirectory.findFilesDirectory(operation.file)?.files?.remove(operation.file)
            }
            is Operation.AddDirectory -> {
                operation.toDirectory.files.firstOrNull { it.name == operation.directory.name }?.let {
                    throw DirectoryAlreadyExistsException()
                }
                operation.toDirectory.directories.add(operation.directory)
            }
            is Operation.DeleteDirectory -> {
                rootDirectory.findContainingDirectory(operation.directory)?.directories?.remove(operation.directory)
            }
        }
    }
}

class FileAlreadyExistsException: Exception("A file with this name already exists")
class DirectoryAlreadyExistsException: Exception("A directory with this name already exists")