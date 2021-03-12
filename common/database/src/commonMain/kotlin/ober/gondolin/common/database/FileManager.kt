package ober.gondolin.common.database

import ober.gondolin.common.database.models.index.Directory
import ober.gondolin.common.database.models.index.File
import ober.gondolin.common.database.models.index.Index

class FileManager(key: String) {
    val database = GondolinDatabase(getCredentialsDriver(key))

//    val index: Index by lazy {
//
//    }

//    fun getRootDirectory(): Directory {
//        val files = getAllFiles()
//
//        files.forEach {
//
//        }
//    }

//    fun getAllFiles(): List<File> {
//        val credentials = database.gondolinDatabaseQueries.selectAllBasicInfoCredentials().executeAsList()
//        val images = database.gondolinDatabaseQueries.selectAllBasicInfoImage().executeAsList()
//        val videos = database.gondolinDatabaseQueries.selectAllBasicInfoVideo().executeAsList()
//        val files = database.gondolinDatabaseQueries.selectAllBasicInfoFile().executeAsList()
//
//        val allFiles = mutableListOf<File>()
//        credentials.forEach {
//            allFiles.add(File(name = it.name, path = it.path))
//        }
//        images.forEach {
//            allFiles.add(File(name = it.name, path = it.path))
//        }
//        videos.forEach {
//            allFiles.add(File(name = it.name, path = it.path))
//        }
//        files.forEach {
//            allFiles.add(File(name = it.name, path = it.path))
//        }
//        return allFiles
//    }

//    fun createNewIndex(): Index {
//
//    }
}