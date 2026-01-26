package simulations.data

import io.gatling.core.Predef._
import requests.data.PublicationRequests.httpProtocol
import scenarios.data.PublicationScenarios
import utils.auth.OAuthAPI
import utils.auth.OAuthAPI.config.{onceUsers, rampUpUsers, rampUpUsersDuration}

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class CreatePublicationPddaAndCivilAndFamilySimulation extends Simulation {
  private val createPublicationPddaAndCivilAndFamilyExec = scenario("CreatePublicationPddaAndCivilAndFamily")
    .exec(OAuthAPI.authData) // authenticate once per user
    .randomSwitch(
      50.0 -> PublicationScenarios.createPublicationCivilAndFamilyOnly,
      50.0 -> PublicationScenarios.createPublicationCrownFirmPdda
    )


  setUp(
    createPublicationPddaAndCivilAndFamilyExec.inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during (rampUpUsersDuration seconds)
    )
  ).protocols(httpProtocol)
    .assertions(
      details("Create Publication Civil And Family request").responseTime.percentile(90).lt(1000),
      details("Create Publication Crown Firm Pdda request").responseTime.percentile(90).lt(1000)
    )
    .assertions(
      details("Create Publication Civil And Family request").responseTime.percentile(95).lt(1500),
      details("Create Publication Crown Firm Pdda request").responseTime.percentile(95).lt(1000)
    )
    .assertions(
      details("Create Publication Civil And Family request").responseTime.percentile(99).lt(2000),
      details("Create Publication Crown Firm Pdda request").responseTime.percentile(99).lt(1000)
    )
}


