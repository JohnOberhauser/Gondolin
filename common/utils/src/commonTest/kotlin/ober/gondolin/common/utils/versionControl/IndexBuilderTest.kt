package ober.gondolin.common.utils.versionControl

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
        val dir1 = File.Directory("dir1")
        val dir2 = File.Directory("dir2")
        val dir3 = File.Directory("dir3")

        index.addCommits(
            listOf(
                Operation.AddFile(
                    file = file1,
                    toDirectory = index.rootDirectory
                ),
                Operation.AddFile(
                    file = dir1,
                    toDirectory = index.rootDirectory
                ),
                Operation.AddFile(
                    file = dir2,
                    toDirectory = dir1
                ),
                Operation.AddFile(
                    file = dir3,
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
            index.filesWithPath(dir3.fullPath!!).contains(file2)
        }

        assertTrue {
            dir3.files.size == 2
        }

        assertTrue {
            dir3.path == listOf(
                index.rootDirectory.name,
                dir1.name,
                dir2.name
            )
        }

        assertTrue {
            index.allFiles.contains(file3)
        }

        index.addCommit(
            operation = Operation.DeleteFile(
                file = file3,
                fromDirectory = dir3
            )
        )

        assertTrue {
            !index.allFiles.contains(file3)
        }

        assertTrue {
            dir3.files.size == 1
        }
    }
}