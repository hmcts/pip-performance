package simulations.data

import io.gatling.core.Predef._
import requests.data.PublicationRequests.httpProtocol
import scenarios.data.PublicationScenarios
import utils.auth.OAuthAPI.config.{onceUsers, rampUpUsers, rampUpUsersDuration}

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class generateArtefactSummary100CasesSimulation extends Simulation {

  private val generateArtefactSummaryExec = PublicationScenarios.generateArtefactSummaryHundredCasesScenario
    .inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during (rampUpUsersDuration seconds)
    )

  setUp(generateArtefactSummaryExec)
    .protocols(httpProtocol)
    .assertions(
      details("Generate Artefact Summary 100 Cases request").responseTime.percentile(90).lt(1000)
    )
    .assertions(
      details("Generate Artefact Summary 100 Cases request").responseTime.percentile(95).lt(1500)
    )
    .assertions(
      details("Generate Artefact Summary 100 Cases request").responseTime.percentile(99).lt(2000)
    )
}


