package ober.gondolin.common.utils.versionControl

fun Index.addCommit(operation: Operation) {
    when(operation) {
        is Operation.AddFile -> {

            operation.toDirectory.files.firstOrNull { it == operation.file.name }?.let {
                throw FileAlreadyExistsException()
            }
            operation.toDirectory.files.add(operation.file.name)
            operation.file.path = mutableListOf<String>().apply {
                operation.toDirectory.path?.let { addAll(it) }
                add(operation.toDirectory.name)
            }
            allFiles.add(operation.file)
        }
        is Operation.DeleteFile -> {
            operation.fromDirectory.files.remove(operation.file.name)
            operation.file.path = null
            allFiles.remove(operation.file)
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

fun Index.merge(otherIndex: Index) {
    val commitsToApply = otherIndex.commits.filter { commit ->
        commits.find {
            it.hash == commit.hash
        } == null
    }


}

class FileAlreadyExistsException: Exception("A file with this name already exists")