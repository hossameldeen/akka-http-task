package io.github.hossameldeen.AkkaHttpTask

import org.scalatest.FunSuite
import akka.http.scaladsl.testkit.ScalatestRouteTest

import OneJsonProtocolToRuleThemAll._

class TestSuite extends FunSuite with ScalatestRouteTest with Service {


  test("retrieve when nothing is added") {
    Get("/retrieve") ~> route ~> check {
      responseAs[List[Something]] === Nil
    }
  }


  test("add one name & retrieve it") {

    val something = Something("hamada")

    Post("/add", something) ~> route ~> check {
      responseAs[String] === "Thank you sir, the name " + something.name + " has been added!"
    }

    Get("/retrieve") ~> route ~> check {
      responseAs[List[Something]] === List(something)
    }
  }


  test("add 2 different names & retrieve them") {

    val something1 = Something("hamada1")
    val something2 = Something("hamada2")

    Post("/add", something1) ~> route ~> check {
      responseAs[String] === "Thank you sir, the name " + something1.name + " has been added!"
    }

    Post("/add", something2) ~> route ~> check {
      responseAs[String] === "Thank you sir, the name " + something2.name + " has been added!"
    }

    Get("/retrieve") ~> route ~> check {
      responseAs[List[Something]] === List(something1, something2)
    }
  }


  test("add 2 equal names & retrieve them") {

    val something1 = Something("hamada")
    val something2 = Something("hamada")

    Post("/add", something1) ~> route ~> check {
      responseAs[String] === "Thank you sir, the name " + something1.name + " has been added!"
    }

    Post("/add", something2) ~> route ~> check {
      responseAs[String] === "Thank you sir, the name " + something2.name + " has been added!"
    }

    Get("/retrieve") ~> route ~> check {
      responseAs[List[Something]] === List(something1, something2)
    }
  }

}
