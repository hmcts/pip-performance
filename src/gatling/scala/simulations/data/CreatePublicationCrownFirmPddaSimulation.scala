package simulations.data

import io.gatling.core.Predef.{details, _}
import requests.data.PublicationRequests.httpProtocol
import scenarios.data.PublicationScenarios
import utils.auth.OAuthAPI.config.{onceUsers, rampUpUsers, rampUpUsersDuration}

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class CreatePublicationCrownFirmPddaSimulation extends Simulation {

  private val createPublicationCrownFirmPddaSExec = PublicationScenarios.CreatePublicationCrownFirmPddaScenario
    .inject(
      atOnceUsers(onceUsers),
      rampUsers(2000) during (3600)
    )

  setUp(createPublicationCrownFirmPddaSExec)
    .protocols(httpProtocol)
    .assertions(
      details("Create Publication Crown Firm Pdda request").responseTime.percentile(90).lt(500)
    )
    .assertions(
      details("Create Publication Crown Firm Pdda request").responseTime.percentile(95).lt(1000)
    )
    .assertions(
      details("Create Publication Crown Firm Pdda request").responseTime.percentile(99).lt(2000)
    )
}


