package simulations.publication

import io.gatling.core.Predef._
import requests.publication.SubscriptionRequests.httpProtocol
import scenarios.publication.SubscriptionScenarios
import utils.auth.OAuthAPI
import utils.auth.OAuthAPI.config.{onceUsers, rampUpUsers, rampUpUsersDuration}

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class notifySubscriptionSimulation50Cases extends Simulation {

  private val notifySubscriptionExec = scenario("Notify Subscription 50 Cases")
    .exec(OAuthAPI.authPublication) // execute once per virtual user
    .exec(SubscriptionScenarios.notifySubscriptionFiftyCasesFlow)

  setUp(
    notifySubscriptionExec.inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during rampUpUsersDuration.seconds
    )
  )
    .protocols(httpProtocol)
    .assertions(
      details("Notify Subscription 50 Cases request").responseTime.percentile(90).lt(2000)
    )
    .assertions(
      details("Notify Subscription 50 Cases request").responseTime.percentile(95).lt(2500)
    )
    .assertions(
      details("Notify Subscription 50 Cases request").responseTime.percentile(99).lt(3000)
    )
}


