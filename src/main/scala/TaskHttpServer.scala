package io.github.hossameldeen.AkkaHttpTask

import akka.actor.ActorSystem

import akka.stream.ActorMaterializer

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model._
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding

import OneJsonProtocolToRuleThemAll._

import scala.concurrent.{Future, ExecutionContext}

import scala.io.StdIn.readLine

case class Something(name: String)

object Server {
  def main(args: Array[String]): Unit = {

    implicit val system: ActorSystem = ActorSystem("RequestsHandlerSystem")
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    implicit val execContext: ExecutionContext = system.dispatcher

    val route: Route =
      path("add") {
        get {
          complete(Something("hamada"))
        }
      } ~
      path("retrieve") {
        get {
          complete(List("ahmed", "mohamed", "hamada"))
        }
      }

    val bindingFuture: Future[ServerBinding] = Http().bindAndHandle(route, "localhost", 8080)

    println("Server running at localhost:8080. Press Enter to stop...")
    readLine()
    println("Stopping the server...")
    bindingFuture
      .flatMap(_.unbind())
      .onComplete( _ => {
        println("Server terminated. Terminating the system...")
        system.terminate().onComplete(_ => println("System terminated."))
      })
  }
}