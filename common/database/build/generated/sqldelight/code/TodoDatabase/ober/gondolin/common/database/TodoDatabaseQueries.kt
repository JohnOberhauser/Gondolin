package ober.gondolin.common.database

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.Transacter
import kotlin.Any
import kotlin.Boolean
import kotlin.Long
import kotlin.String

interface TodoDatabaseQueries : Transacter {
  fun <T : Any> selectAll(mapper: (
    id: Long,
    orderNum: Long,
    text: String,
    isDone: Boolean
  ) -> T): Query<T>

  fun selectAll(): Query<TodoItemEntity>

  fun <T : Any> select(id: Long, mapper: (
    id: Long,
    orderNum: Long,
    text: String,
    isDone: Boolean
  ) -> T): Query<T>

  fun select(id: Long): Query<TodoItemEntity>

  fun getLastInsertId(): Query<Long>

  fun add(text: String)

  fun setText(text: String, id: Long)

  fun setDone(isDone: Boolean, id: Long)

  fun delete(id: Long)

  fun clear()
}
