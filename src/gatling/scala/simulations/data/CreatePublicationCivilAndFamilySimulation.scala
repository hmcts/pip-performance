package simulations.data

import io.gatling.core.Predef._
import requests.data.PublicationRequests.httpProtocol
import scenarios.data.PublicationScenarios
import utils.auth.OAuthAPI.config.{onceUsers, rampUpUsers, rampUpUsersDuration}

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class CreatePublicationCivilAndFamilySimulation extends Simulation {

  private val createPublicationCivilFamilyExec = PublicationScenarios.CreatePublicationCivilAndFamilyScenario
    .inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during (rampUpUsersDuration seconds)
    )

  setUp(createPublicationCivilFamilyExec)
    .protocols(httpProtocol)
    .assertions(
      details("Create Publication Civil And Family request").responseTime.percentile(90).lt(1000),
      details("Create Publication Civil Next Day request").responseTime.percentile(90).lt(1000),
      details("Create Publication Family request").responseTime.percentile(90).lt(1000)
    )
    .assertions(
      details("Create Publication Civil And Family request").responseTime.percentile(95).lt(1500),
      details("Create Publication Civil Next Day request").responseTime.percentile(95).lt(1500),
      details("Create Publication Family request").responseTime.percentile(95).lt(1500)
    )
    .assertions(
      details("Create Publication Civil And Family request").responseTime.percentile(99).lt(2000),
      details("Create Publication Civil Next Day request").responseTime.percentile(99).lt(2000),
      details("Create Publication Family request").responseTime.percentile(99).lt(2000)
    )
}


