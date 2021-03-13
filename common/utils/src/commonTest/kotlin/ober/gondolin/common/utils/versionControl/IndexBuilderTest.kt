package ober.gondolin.common.utils.versionControl

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ober.gondolin.common.database.models.index.*
import kotlin.test.Test
import kotlin.test.assertTrue

class IndexBuilderTest {

    @Test
    fun testBuildingIndex() {
        val index = Index()
        val file1 = File.CredentialsFile(name = "file1", username = "some username")
        val file2 = File.VideoFile("file2")
        val file3 = File.ImageFile("file3")
        val dir1 = Directory("dir1")
        val dir2 = Directory("dir2")
        val dir3 = Directory("dir3")

        index.addCommits(
            listOf(
                Operation.AddFile(
                    file = file1,
                    toDirectory = index.rootDirectory
                ),
                Operation.AddDirectory(
                    directory = dir1,
                    toDirectory = index.rootDirectory
                ),
                Operation.AddDirectory(
                    directory = dir2,
                    toDirectory = dir1
                ),
                Operation.AddDirectory(
                    directory = dir3,
                    toDirectory = dir2
                ),
                Operation.AddFile(
                    file = file2,
                    toDirectory = dir3
                ),
                Operation.AddFile(
                    file = file3,
                    toDirectory = dir3
                )
            )
        )

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
            operation = Operation.DeleteFile(
                file = file3
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