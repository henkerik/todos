package net.bzzt.todo.backend.akka

import akka.stream._

trait FlowMaterializerProvider {
  implicit val materializer: Materializer
}
