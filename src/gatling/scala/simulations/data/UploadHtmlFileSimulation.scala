package simulations.data

import io.gatling.core.Predef._
import requests.data.UploadHtmlFileRequest.httpProtocol
import scenarios.data.UploadHtmlFileScenario
import utils.auth.OAuthAPI.config.{onceUsers, rampUpUsers, rampUpUsersDuration}

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class UploadHtmlFileSimulation extends Simulation {

  private val uploadHtmlFileExec = UploadHtmlFileScenario.UploadHtmlPublicationScenario
    .inject(
      atOnceUsers(onceUsers),
      rampUsers(200) during (1)
    )

  setUp(uploadHtmlFileExec)
    .protocols(httpProtocol)
    .assertions(
      details("Create HTML Publication request").responseTime.percentile(90).lt(800)
    )
    .assertions(
      details("Create HTML Publication request").responseTime.percentile(95).lt(1000)
    )
    .assertions(
      details("Create HTML Publication request").responseTime.percentile(99).lt(2000),
    )
}


