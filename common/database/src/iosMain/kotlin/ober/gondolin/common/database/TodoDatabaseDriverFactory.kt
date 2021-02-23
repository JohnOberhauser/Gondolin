package ober.gondolin.common.database

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

@Suppress("FunctionName") // Factory function
fun TodoDatabaseDriver(): SqlDriver =
    NativeSqliteDriver(TodoDatabase.Schema, "TodoDatabase.db")
