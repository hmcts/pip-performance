package simulations.data

import io.gatling.core.Predef._
import requests.data.PublicationRequests.httpProtocol
import scenarios.data.PublicationScenarios
import utils.auth.OAuthAPI
import utils.auth.OAuthAPI.config.{onceUsers, rampUpUsers, rampUpUsersDuration}

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class CreatePublication4MBSimulation extends Simulation {

  private val createPublication4MBExec = scenario("Create Publication Civil And Family 4MB")
    .exec(OAuthAPI.authData) // authenticate once per user
    .exec(PublicationScenarios.create4MB)


  setUp(
    createPublication4MBExec.inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during (rampUpUsersDuration seconds)
    )
  ).protocols(httpProtocol)
    .assertions(
      details("Create Publication Civil And Family 4MB request").responseTime.percentile(90).lt(3000)
    )
    .assertions(
      details("Create Publication Civil And Family 4MB request").responseTime.percentile(95).lt(3500)
    )
    .assertions(
      details("Create Publication Civil And Family 4MB request").responseTime.percentile(99).lt(4000)
    )
}


