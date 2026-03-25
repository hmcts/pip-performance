package simulations.data

import io.gatling.core.Predef._
import requests.data.PublicationRequests.httpProtocol
import scenarios.data.{PublicationScenarios, UploadHtmlFileScenario}
import utils.auth.OAuthAPI
import utils.auth.OAuthAPI.config.{onceUsers, rampUpUsers, rampUpUsersDuration}

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class CreatePublicationAllServicesSimulation extends Simulation {
  private val createPublicationAllServicesExec = scenario("CreatePublicationAllServicesSimulation")
    .exec(OAuthAPI.authData) // authenticate once per user
    .randomSwitch(
      9.0 -> PublicationScenarios.createPublicationCivilAndFamilyOnly, //228 requests
      18.0 -> PublicationScenarios.createPublicationCrownFirmPdda, //455 requests
      24.0 -> UploadHtmlFileScenario.uploadHtmlPublicationFlow, //650 requests
      15.0 -> PublicationScenarios.createPublicationMagsPublicAdultCrimePortal, //390 requests
      34.0 -> PublicationScenarios.createPublicationMagsPublicCommonPlatform //878 requests
    )


  setUp(
    createPublicationAllServicesExec.inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during (rampUpUsersDuration seconds)
    )
  ).protocols(httpProtocol)
    .assertions(
      details("Create Publication Civil And Family request").responseTime.percentile(90).lt(1000),
      details("Create Publication Crown Firm Pdda request").responseTime.percentile(90).lt(1000),
      details("Create HTML Publication request").responseTime.percentile(90).lt(1000),
      details("Create Publication Mags Public Daily Crime Portal request").responseTime.percentile(90).lt(1000),
      details("Create Publication Mags Public Common Platform request").responseTime.percentile(90).lt(1000)
    )
    .assertions(
      details("Create Publication Civil And Family request").responseTime.percentile(95).lt(1500),
      details("Create Publication Crown Firm Pdda request").responseTime.percentile(95).lt(1500),
      details("Create HTML Publication request").responseTime.percentile(95).lt(1500),
      details("Create Publication Mags Public Daily Crime Portal request").responseTime.percentile(95).lt(1500),
      details("Create Publication Mags Public Common Platform request").responseTime.percentile(95).lt(1500)
    )
    .assertions(
      details("Create Publication Civil And Family request").responseTime.percentile(99).lt(2000),
      details("Create Publication Crown Firm Pdda request").responseTime.percentile(99).lt(2000),
      details("Create HTML Publication request").responseTime.percentile(99).lt(2000),
      details("Create Publication Mags Public Daily Crime Portal request").responseTime.percentile(99).lt(2000),
      details("Create Publication Mags Public Common Platform request").responseTime.percentile(99).lt(2000)
    )
}


