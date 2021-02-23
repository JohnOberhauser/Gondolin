package ober.gondolin.common.database

import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual fun getCredentialsDriver(): SqlDriver =
    AndroidSqliteDriver(
        schema = CredentialsDatabase.Schema,
        context = DatabaseApplicationContext.application!!,
        name = "CredentialsDatabase.db"
    )