package ober.gondolin.database.database

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.TransacterImpl
import com.squareup.sqldelight.db.SqlCursor
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.internal.copyOnWriteList
import kotlin.Any
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.collections.MutableList
import kotlin.jvm.JvmField
import kotlin.reflect.KClass
import ober.gondolin.common.database.TodoDatabaseQueries
import ober.gondolin.common.database.TodoItemEntity
import ober.gondolin.database.TodoDatabase

internal val KClass<TodoDatabase>.schema: SqlDriver.Schema
  get() = TodoDatabaseImpl.Schema

internal fun KClass<TodoDatabase>.newInstance(driver: SqlDriver): TodoDatabase =
    TodoDatabaseImpl(driver)

private class TodoDatabaseImpl(
  driver: SqlDriver
) : TransacterImpl(driver), TodoDatabase {
  override val todoDatabaseQueries: TodoDatabaseQueriesImpl = TodoDatabaseQueriesImpl(this, driver)

  object Schema : SqlDriver.Schema {
    override val version: Int
      get() = 1

    override fun create(driver: SqlDriver) {
      driver.execute(null, """
          |CREATE TABLE IF NOT EXISTS TodoItemEntity (
          |    id INTEGER PRIMARY KEY AUTOINCREMENT,
          |    orderNum INTEGER NOT NULL,
          |    text TEXT NOT NULL,
          |    isDone INTEGER NOT NULL DEFAULT 0
          |)
          """.trimMargin(), 0)
    }

    override fun migrate(
      driver: SqlDriver,
      oldVersion: Int,
      newVersion: Int
    ) {
    }
  }
}

private class TodoDatabaseQueriesImpl(
  private val database: TodoDatabaseImpl,
  private val driver: SqlDriver
) : TransacterImpl(driver), TodoDatabaseQueries {
  internal val selectAll: MutableList<Query<*>> = copyOnWriteList()

  internal val select: MutableList<Query<*>> = copyOnWriteList()

  internal val getLastInsertId: MutableList<Query<*>> = copyOnWriteList()

  override fun <T : Any> selectAll(mapper: (
    id: Long,
    orderNum: Long,
    text: String,
    isDone: Boolean
  ) -> T): Query<T> = Query(1589784378, selectAll, driver, "TodoDatabase.sq", "selectAll", """
  |SELECT *
  |FROM TodoItemEntity
  """.trimMargin()) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getLong(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3)!! == 1L
    )
  }

  override fun selectAll(): Query<TodoItemEntity> = selectAll { id, orderNum, text, isDone ->
    TodoItemEntity(
      id,
      orderNum,
      text,
      isDone
    )
  }

  override fun <T : Any> select(id: Long, mapper: (
    id: Long,
    orderNum: Long,
    text: String,
    isDone: Boolean
  ) -> T): Query<T> = SelectQuery(id) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getLong(1)!!,
      cursor.getString(2)!!,
      cursor.getLong(3)!! == 1L
    )
  }

  override fun select(id: Long): Query<TodoItemEntity> = select(id) { id_, orderNum, text, isDone ->
    TodoItemEntity(
      id_,
      orderNum,
      text,
      isDone
    )
  }

  override fun getLastInsertId(): Query<Long> = Query(-279499435, getLastInsertId, driver,
      "TodoDatabase.sq", "getLastInsertId", "SELECT last_insert_rowid()") { cursor ->
    cursor.getLong(0)!!
  }

  override fun add(text: String) {
    driver.execute(530077846, """
    |INSERT INTO TodoItemEntity (orderNum, text)
    |VALUES ((CASE (SELECT COUNT(*) FROM TodoItemEntity) WHEN 0 THEN 1 ELSE (SELECT MAX(orderNum)+1 FROM TodoItemEntity) END), ?)
    """.trimMargin(), 1) {
      bindString(1, text)
    }
    notifyQueries(530077846, {database.todoDatabaseQueries.selectAll +
        database.todoDatabaseQueries.select})
  }

  override fun setText(text: String, id: Long) {
    driver.execute(786190884, """
    |UPDATE TodoItemEntity
    |SET text = ?
    |WHERE id = ?
    """.trimMargin(), 2) {
      bindString(1, text)
      bindLong(2, id)
    }
    notifyQueries(786190884, {database.todoDatabaseQueries.selectAll +
        database.todoDatabaseQueries.select})
  }

  override fun setDone(isDone: Boolean, id: Long) {
    driver.execute(785723513, """
    |UPDATE TodoItemEntity
    |SET isDone = ?
    |WHERE id = ?
    """.trimMargin(), 2) {
      bindLong(1, if (isDone) 1L else 0L)
      bindLong(2, id)
    }
    notifyQueries(785723513, {database.todoDatabaseQueries.selectAll +
        database.todoDatabaseQueries.select})
  }

  override fun delete(id: Long) {
    driver.execute(-958487146, """
    |DELETE FROM TodoItemEntity
    |WHERE id = ?
    """.trimMargin(), 1) {
      bindLong(1, id)
    }
    notifyQueries(-958487146, {database.todoDatabaseQueries.selectAll +
        database.todoDatabaseQueries.select})
  }

  override fun clear() {
    driver.execute(-1694208766, """DELETE FROM TodoItemEntity""", 0)
    notifyQueries(-1694208766, {database.todoDatabaseQueries.selectAll +
        database.todoDatabaseQueries.select})
  }

  private inner class SelectQuery<out T : Any>(
    @JvmField
    val id: Long,
    mapper: (SqlCursor) -> T
  ) : Query<T>(select, mapper) {
    override fun execute(): SqlCursor = driver.executeQuery(-529050393, """
    |SELECT *
    |FROM TodoItemEntity
    |WHERE id = ?
    """.trimMargin(), 1) {
      bindLong(1, id)
    }

    override fun toString(): String = "TodoDatabase.sq:select"
  }
}
