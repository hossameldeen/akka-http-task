package io.github.hossameldeen.AkkaHttpTask

import spray.json._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport

object OneJsonProtocolToRuleThemAll extends DefaultJsonProtocol with SprayJsonSupport  {
  implicit val somethingFormat = jsonFormat1(Something)
}
