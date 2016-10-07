package net.bzzt.todo.backend.akka

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

import akka.http.scaladsl.model._
import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model.headers._

import akka.http.scaladsl.server._
import akka.http.scaladsl.server.Directives._

import akka.http.scaladsl.unmarshalling._
import akka.http.scaladsl.marshalling._

import akka.actor._
import akka.pattern._
import akka.util._

trait TodoRoutes extends TodoMarshalling
  with TodoMappingProvider {

  implicit val timeout: Timeout = 2.seconds

  def routes = {
     extract(_.request.getUri()) { uri =>

      implicit val todoFormat = todoFormatFor(uri.path("/todos").toString)

          pathPrefix("todos") {
          pathEnd {
            get {
              complete(StatusCodes.OK, todoMapping.getList())
            } ~
            post {
              entity(as[TodoUpdate]) { update =>
                val todo = todoMapping.add(update)
                complete(StatusCodes.OK, todo)
              }
            } ~
            delete {
              todoMapping.clear()
              complete(StatusCodes.OK)
            }
          } ~ {
            path(Segment) { id =>
              get {
                todoMapping.get(id) match {
                  case None => complete(StatusCodes.NotFound)
                  case Some(todo) => complete(StatusCodes.OK, todo)
                }
              } ~
              patch {
                entity(as[TodoUpdate]) { update =>
                  todoMapping.update(id, update)  match {
                    case None => complete(StatusCodes.NotFound)
                    case Some(todo) => complete(StatusCodes.OK, todo)
                  }
                }
              } ~
              delete {
                todoMapping.delete(id)
                complete(StatusCodes.OK)
              }
            }
          }
        } ~
        path("") {
          get {
            complete(StatusCodes.OK)
          }
        } ~
        options {
          complete(StatusCodes.OK)
        }
      }
  }
}
