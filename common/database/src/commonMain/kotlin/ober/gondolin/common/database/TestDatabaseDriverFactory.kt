package ober.gondolin.common.database

import com.squareup.sqldelight.db.SqlDriver

@Suppress("FunctionName")
expect fun TestDatabaseDriver(): SqlDriver
