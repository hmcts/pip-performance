package simulations.combined

import io.gatling.core.Predef._
import requests.publication.SubscriptionRequests.httpProtocol
import scenarios.combined.PublicationFlowScenarios
import utils.auth.OAuthAPI.config.rampUpUsersDuration

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class PublicationFlowSimulation extends Simulation {

  private val bulkUploadSubscriptionSetup = PublicationFlowScenarios.testBulkSendSubscription
    .inject(
      atOnceUsers(1),
      rampUsers(0) during (rampUpUsersDuration seconds)
    )

  setUp(bulkUploadSubscriptionSetup)
    .protocols(httpProtocol)
    .assertions(
      details("Create a PI_AAD Account").responseTime.percentile(90).lt(2000)
    )
}


