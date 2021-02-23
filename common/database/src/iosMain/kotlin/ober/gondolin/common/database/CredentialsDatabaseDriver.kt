package ober.gondolin.common.database

import com.squareup.sqldelight.db.SqlDriver

actual fun getCredentialsDriver(): SqlDriver =
    NativeSqliteDriver(TodoDatabase.Schema, "CredentialsDatabase.db")