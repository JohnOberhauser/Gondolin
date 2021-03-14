package ober.gondolin.common.utils.versionControl

import ober.gondolin.common.database.models.index.File
import kotlin.test.Test

class SerializationTest {

    @Test
    fun test() {
        val index = Index()
        val file1 = File.CredentialsFile(name = "file1", username = "some username")

        index.addCommits(
            listOf(
                Operation.AddFile(
                    file = file1,
                    toDirectory = index.rootDirectory
                )
            )
        )

        
    }
}