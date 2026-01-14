package simulations.data

import io.gatling.core.Predef._
import requests.data.PublicationRequests.httpProtocol
import scenarios.data.PublicationScenarios
import utils.auth.OAuthAPI
import utils.auth.OAuthAPI.config.{onceUsers, rampUpUsers, rampUpUsersDuration}

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class CreatePublication2MBSimulation extends Simulation {

  private val createPublication2MBExec = scenario("Create Publication Civil And Family 2MB")
    .exec(OAuthAPI.authData) // authenticate once per user
    .exec(PublicationScenarios.create2MB)


  setUp(
    createPublication2MBExec.inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during (rampUpUsersDuration seconds)
    )
  ).protocols(httpProtocol)
    .assertions(
      details("Create Publication Civil And Family 2MB request").responseTime.percentile(90).lt(2000)
    )
    .assertions(
      details("Create Publication Civil And Family 2MB request").responseTime.percentile(95).lt(2500)
    )
    .assertions(
      details("Create Publication Civil And Family 2MB request").responseTime.percentile(99).lt(3000)
    )
}


