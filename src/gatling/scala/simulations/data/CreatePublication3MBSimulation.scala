package simulations.data

import io.gatling.core.Predef._
import requests.data.PublicationRequests.httpProtocol
import scenarios.data.PublicationScenarios
import utils.auth.OAuthAPI
import utils.auth.OAuthAPI.config.{onceUsers, rampUpUsers, rampUpUsersDuration}

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class CreatePublication3MBSimulation extends Simulation {

  private val createPublication3MBExec = scenario("Create Publication Civil And Family 3MB")
    .exec(OAuthAPI.authData) // authenticate once per user
    .exec(PublicationScenarios.create3MB)


  setUp(
    createPublication3MBExec.inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during (rampUpUsersDuration seconds)
    )
  ).protocols(httpProtocol)
    .assertions(
      details("Create Publication Civil And Family 3MB request").responseTime.percentile(90).lt(2500)
    )
    .assertions(
      details("Create Publication Civil And Family 3MB request").responseTime.percentile(95).lt(3000)
    )
    .assertions(
      details("Create Publication Civil And Family 3MB request").responseTime.percentile(99).lt(3500)
    )
}


