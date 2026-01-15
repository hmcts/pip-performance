package simulations.combined

import io.gatling.core.Predef._
import requests.publication.SubscriptionRequests.httpProtocol
import scenarios.combined.PublicationFlowScenarios
import utils.auth.OAuthAPI
import utils.auth.OAuthAPI.config.rampUpUsersDuration

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class PublicationFlowSimulation extends Simulation {
  // ScenarioBuilder: authenticate once, then execute the flow
  private val bulkUploadSubscriptionSetup = scenario("Publication Flow with Auth")
    .exec(OAuthAPI.authAccount) // authenticate account once per virtual user
    .exec(OAuthAPI.authData) // authenticate publication once per virtual user
    .exec(PublicationFlowScenarios.testBulkSendSubscriptionFlow)

  // Injection: mix of atOnceUsers and rampUsers
  setUp(
    bulkUploadSubscriptionSetup.inject(
      atOnceUsers(1),
      rampUsers(0) during rampUpUsersDuration.seconds
    )
  )
    .protocols(httpProtocol)
    .assertions(
      details("Create a PI_AAD Account").responseTime.percentile(90).lt(2000)
    )
}


