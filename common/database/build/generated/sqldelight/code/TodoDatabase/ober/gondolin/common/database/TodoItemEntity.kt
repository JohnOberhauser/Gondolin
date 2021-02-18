package ober.gondolin.common.database

import kotlin.Boolean
import kotlin.Long
import kotlin.String

data class TodoItemEntity(
  val id: Long,
  val orderNum: Long,
  val text: String,
  val isDone: Boolean
) {
  override fun toString(): String = """
  |TodoItemEntity [
  |  id: $id
  |  orderNum: $orderNum
  |  text: $text
  |  isDone: $isDone
  |]
  """.trimMargin()
}
