package ober.gondolin.common.database

import com.squareup.sqldelight.db.SqlDriver

expect fun getCredentialsDriver(key: String): SqlDriver