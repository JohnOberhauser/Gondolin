package ober.gondolin.common.database

import com.squareup.sqldelight.db.SqlDriver

actual fun getCredentialsDriver(key: String): SqlDriver =
    NativeSqliteDriver(GondolinDatabase.Schema, "GondolinDatabase.db")