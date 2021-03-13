package ober.gondolin.common.utils.versionControl

fun Index.addCommit(operation: Operation) {
    when(operation) {
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
            operation.toDirectory.directories.firstOrNull { it.name == operation.directory.name }?.let {
                throw DirectoryAlreadyExistsException()
            }
            operation.toDirectory.directories.add(operation.directory)
        }
        is Operation.DeleteDirectory -> {
            rootDirectory.findContainingDirectory(operation.directory)?.directories?.remove(operation.directory)
        }
    }

    val previousHash = commits.lastOrNull()?.hash ?: ""

    commits.add(
        Commit(
            operation = operation,
            previousHash = previousHash
        )
    )
}

fun Index.addCommits(operations: List<Operation>) {
    operations.forEach { operation ->
        addCommit(operation)
    }
}

class FileAlreadyExistsException: Exception("A file with this name already exists")
class DirectoryAlreadyExistsException: Exception("A directory with this name already exists")