package ober.gondolin.common.utils.versionControl

import ober.gondolin.common.database.models.index.*
import kotlin.test.Test
import kotlin.test.assertTrue

class IndexBuilderTest {

    @Test
    fun testBuildingIndex() {
        val commits = mutableListOf<Commit>()
        val index = Index()
        val file1 = File.CredentialsFile(name = "file1", username = "some username")
        val file2 = File.VideoFile("file2")
        val file3 = File.ImageFile("file3")
        val dir1 = Directory("dir1")
        val dir2 = Directory("dir2")
        val dir3 = Directory("dir3")

        commits.addAll(
            listOf(
                Commit(
                    operation = Operation.AddFile(
                        file = file1,
                        toDirectory = index.rootDirectory
                    )
                ),
                Commit(
                    operation = Operation.AddDirectory(
                        directory = dir1,
                        toDirectory = index.rootDirectory
                    )
                ),
                Commit(
                    operation = Operation.AddDirectory(
                        directory = dir2,
                        toDirectory = dir1
                    )
                ),
                Commit(
                    operation = Operation.AddDirectory(
                        directory = dir3,
                        toDirectory = dir2
                    )
                ),
                Commit(
                    operation = Operation.AddFile(
                        file = file2,
                        toDirectory = dir3
                    )
                ),
                Commit(
                    operation = Operation.AddFile(
                        file = file3,
                        toDirectory = dir3
                    )
                )
            )
        )

        index.addCommits(commits)

        assertTrue {
            index.rootDirectory
                .directories.first()
                .directories.first()
                .directories.first()
                .files.first() == file2
        }

        assertTrue {
            index.rootDirectory
                .directories.first()
                .directories.first()
                .directories.first()
                .files.size == 2
        }

        index.addCommit(
            Commit(
                operation = Operation.DeleteFile(
                    file = file3
                )
            )
        )

        assertTrue {
            index.rootDirectory
                .directories.first()
                .directories.first()
                .directories.first()
                .files.size == 1
        }
    }
}