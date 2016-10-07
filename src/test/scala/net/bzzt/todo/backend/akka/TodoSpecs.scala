package net.bzzt.todo.backend.akka

import org.scalatest._

class TodoSpecs extends Suite
  with WordSpecLike
  with ShouldMatchers
   {

  "A Todo" should {
    "correctly initialize itself with default values" in {
      val todo = Todo("1", "Learn Scala")

      todo.id should equal("1")
      todo.title should equal("Learn Scala")
      todo.completed should equal(false)
    }

    "correctly update itself with given a 'TodoUpdate'" in {
      val todo = Todo("1", "Learn Scala")

      val todoUpdate = TodoUpdate(
        title = None,
        completed = Some(true),
        order = Some(42)
      )

      val newTodo = Todo(todo, todoUpdate)

      newTodo.id should equal("1")
      newTodo.title should equal("Learn Scala")
      newTodo.completed should equal(true)
      newTodo.order should equal(42)
    }

    "correctly create a 'Todo' based on a title and a 'TodoUpdate'" in {
      val todoUpdate = TodoUpdate(
        title = None,
        completed = Some(true),
        order = None
      )

      val todo = Todo("Learn ScalaTest", todoUpdate)

      todo.title should equal("Learn ScalaTest")
      todo.completed should equal(true)
      todo.order should equal(0)
    }
  }
}
