package net.bzzt.todo.backend.akka

class TodoMapping() {

  var mapping: Map[String, Todo] = Map()

  def getList():Iterable[Todo] = {
    mapping.values
  }

  def add(update:TodoUpdate) = {
    val todo = update.title.map(value => Todo(value, update))
    mapping = mapping ++ todo.map(todo => todo.id.toString -> todo)
    todo
  }

  def clear(): Unit = {
    mapping = Map()
  }

  def get(id: String): Option[Todo] = {
    mapping.get(id)
  }

  def update(id: String, update: TodoUpdate): Option[Todo] = {
    get(id) match {
      case None => None
      case Some(todo) => {
        val newTodo = Todo(todo, update)
        mapping = mapping.updated(id, newTodo)
        Some(newTodo)
      }
    }
  }

  def delete(id: String): Unit = {
    mapping = mapping.filter {
      case (todoId, todo) => todoId != id
    }
  }
}
