package simulations.data

import io.gatling.core.Predef._
import requests.data.PublicationRequests.httpProtocol
import scenarios.data.PublicationScenarios
import utils.auth.OAuthAPI.config.{onceUsers, rampUpUsers, rampUpUsersDuration}

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class CreatePublication1MBSimulation extends Simulation {

  private val createPublication1MBExec = PublicationScenarios.CreatePublicationCivilAndFamily1MBScenario
    .inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during (rampUpUsersDuration seconds)
    )

  setUp(createPublication1MBExec)
    .protocols(httpProtocol)
    .assertions(
      details("Create Publication Civil And Family 1MB request").responseTime.percentile(90).lt(1000)
    )
    .assertions(
      details("Create Publication Civil And Family 1MB request").responseTime.percentile(95).lt(1500)
    )
    .assertions(
      details("Create Publication Civil And Family 1MB request").responseTime.percentile(99).lt(2000)
    )
}


