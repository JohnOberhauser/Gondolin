package ober.gondolin.common.database

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import java.io.File

actual fun getCredentialsDriver(): SqlDriver {
    val databasePath = File(System.getProperty("java.io.tmpdir"), "CredentialsDatabase.db")
    val driver = JdbcSqliteDriver(url = "jdbc:sqlite:${databasePath.absolutePath}")
    CredentialsDatabase.Schema.create(driver)

    return driver
}
