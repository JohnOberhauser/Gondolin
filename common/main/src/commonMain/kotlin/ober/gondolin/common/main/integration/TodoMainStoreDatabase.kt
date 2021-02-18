package ober.gondolin.common.main.integration

import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.completable.completableFromFunction
import com.badoo.reaktive.completable.subscribeOn
import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.observable.mapIterable
import com.badoo.reaktive.scheduler.ioScheduler
import com.squareup.sqldelight.Query
import ober.gondolin.common.database.TodoDatabaseQueries
import ober.gondolin.common.database.TodoItemEntity
import ober.gondolin.common.database.asObservable
import ober.gondolin.common.main.TodoItem
import ober.gondolin.common.main.store.TodoMainStoreProvider

internal class TodoMainStoreDatabase(
    private val queries: TodoDatabaseQueries
) : TodoMainStoreProvider.Database {

    override val updates: Observable<List<TodoItem>> =
        queries
            .selectAll()
            .asObservable(Query<TodoItemEntity>::executeAsList)
            .mapIterable { it.toItem() }

    private fun TodoItemEntity.toItem(): TodoItem =
        TodoItem(
            id = id,
            order = orderNum,
            text = text,
            isDone = isDone
        )

    override fun setDone(id: Long, isDone: Boolean): Completable =
        completableFromFunction { queries.setDone(id = id, isDone = isDone) }
            .subscribeOn(ioScheduler)

    override fun delete(id: Long): Completable =
        completableFromFunction { queries.delete(id = id) }
            .subscribeOn(ioScheduler)

    override fun add(text: String): Completable =
        completableFromFunction { queries.add(text = text) }
            .subscribeOn(ioScheduler)
}
