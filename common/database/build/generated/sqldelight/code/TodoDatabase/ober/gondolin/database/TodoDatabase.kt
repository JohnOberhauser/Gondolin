package ober.gondolin.database

import com.squareup.sqldelight.Transacter
import com.squareup.sqldelight.db.SqlDriver
import ober.gondolin.common.database.TodoDatabaseQueries
import ober.gondolin.database.database.newInstance
import ober.gondolin.database.database.schema

interface TodoDatabase : Transacter {
  val todoDatabaseQueries: TodoDatabaseQueries

  companion object {
    val Schema: SqlDriver.Schema
      get() = TodoDatabase::class.schema

    operator fun invoke(driver: SqlDriver): TodoDatabase = TodoDatabase::class.newInstance(driver)}
}
