package io.github.hossameldeen.AkkaHttpTask

import spray.json._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport

object OneJsonProtocolToRuleThemAll extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val somethingFormat = jsonFormat1(Something)
}
