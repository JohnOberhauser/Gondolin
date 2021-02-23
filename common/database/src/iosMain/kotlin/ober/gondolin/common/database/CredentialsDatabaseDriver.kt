package ober.gondolin.common.database

import com.squareup.sqldelight.db.SqlDriver

actual fun getCredentialsDriver(key: String): SqlDriver =
    NativeSqliteDriver(CredentialsDatabase.Schema, "CredentialsDatabase.db")