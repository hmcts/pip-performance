package simulations.data

import io.gatling.core.Predef._
import requests.data.PublicationRequests.httpProtocol
import scenarios.data.PublicationScenarios
import utils.auth.OAuthAPI.config.{onceUsers, rampUpUsers, rampUpUsersDuration}

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class CreatePublication4MBSimulation extends Simulation {

  private val createPublication4MBExec = PublicationScenarios.CreatePublicationCivilAndFamily4MBScenario
    .inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during (rampUpUsersDuration seconds)
    )

  setUp(createPublication4MBExec)
    .protocols(httpProtocol)
    .assertions(
      details("Create Publication Civil And Family 4MB request").responseTime.percentile(90).lt(1000)
    )
    .assertions(
      details("Create Publication Civil And Family 4MB request").responseTime.percentile(95).lt(1500)
    )
    .assertions(
      details("Create Publication Civil And Family 4MB request").responseTime.percentile(99).lt(2000)
    )
}


