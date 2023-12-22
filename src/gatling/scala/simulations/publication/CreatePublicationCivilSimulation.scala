package simulations.publication

import io.gatling.core.Predef.{details, _}
import requests.publication.PublicationRequests.httpProtocol
import scenarios.publication.PublicationScenarios
import utils.auth.OAuthAPI.config.{onceUsers, rampUpUsers, rampUpUsersDuration}

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class CreatePublicationCivilSimulation extends Simulation {

  private val createPublicationCivilExec = PublicationScenarios.CreatePublicationCivilScenario
    .inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during (rampUpUsersDuration seconds)
    )

  setUp(createPublicationCivilExec)
    .protocols(httpProtocol)
    .assertions(
      details("Create Publication Civil request").responseTime.percentile(90).lt(1000)
    )
    .assertions(
      details("Create Publication Civil request").responseTime.percentile(95).lt(1500)
    )
    .assertions(
      details("Create Publication Civil request").responseTime.percentile(99).lt(2000)
    )
}


