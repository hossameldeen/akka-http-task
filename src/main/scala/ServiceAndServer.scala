package io.github.hossameldeen.AkkaHttpTask

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer

import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._

import scala.concurrent.{Future, ExecutionContext}
import scala.io.StdIn.readLine

import OneJsonProtocolToRuleThemAll._

case class Something(name: String)

trait Service {

  var things: List[Something] = Nil

  val route: Route =
    path("add") {
      post {
        entity(as[Something]) { something => {
            things = things :+ something
            complete("Thank you sir, the name " + something.name + " has been added!")
          }
        } ~
        complete("Message body must contain a JSON object with exactly one member called 'name'.")
      } ~
      complete("There's a POST here, that's probably what you're looking for.")
    } ~
    path("retrieve") {
      get {
        complete(things)
      }
    } ~
    complete("Either GET /retrieve. Or POST /add with a message body {\"name\": <someName>}. Replace <someName>.")

}

object Server extends Service {

  def start() = {
    implicit val system: ActorSystem = ActorSystem("RequestsHandlerSystem")
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    implicit val execContext: ExecutionContext = system.dispatcher

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