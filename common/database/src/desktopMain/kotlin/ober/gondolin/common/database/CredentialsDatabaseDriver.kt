package ober.gondolin.common.database

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import org.sqlite.mc.SQLiteMCSqlCipherConfig
import java.io.File

actual fun getCredentialsDriver(key: String): SqlDriver {
    val databasePath = File(System.getProperty("java.io.tmpdir"), "GondolinDatabase.db")
    val driver = JdbcSqliteDriver(
        url = "jdbc:sqlite:${databasePath.absolutePath}",
        properties = SQLiteMCSqlCipherConfig().withKey(key).toProperties()
    )
    GondolinDatabase.Schema.create(driver)

    return driver
}
