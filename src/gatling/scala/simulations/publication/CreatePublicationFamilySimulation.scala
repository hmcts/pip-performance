package simulations.publication

import io.gatling.core.Predef.{details, _}
import requests.publication.PublicationRequests.httpProtocol
import scenarios.publication.PublicationScenarios
import utils.auth.OAuthAPI.config.{onceUsers, rampUpUsers, rampUpUsersDuration}

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class CreatePublicationFamilySimulation extends Simulation {

  private val createPublicationFamilyExec = PublicationScenarios.CreatePublicationFamilyScenario
    .inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during (rampUpUsersDuration seconds)
    )

  setUp(createPublicationFamilyExec)
    .protocols(httpProtocol)
    .assertions(
      details("Create Publication Family request").responseTime.percentile(90).lt(1000)
    )
    .assertions(
      details("Create Publication Family request").responseTime.percentile(95).lt(1500)
    )
    .assertions(
      details("Create Publication Family request").responseTime.percentile(99).lt(2000)
    )
}


