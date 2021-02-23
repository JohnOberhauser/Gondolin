package ober.gondolin.common.database

import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

actual fun getCredentialsDriver(key: String): SqlDriver =
    AndroidSqliteDriver(
        schema = CredentialsDatabase.Schema,
        context = DatabaseApplicationContext.application!!,
        name = "CredentialsDatabase.db",
        factory = SupportFactory(SQLiteDatabase.getBytes(key.toCharArray()))
    )