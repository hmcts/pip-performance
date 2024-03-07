package simulations.publication

import io.gatling.core.Predef._
import requests.publication.SubscriptionRequests.httpProtocol
import scenarios.publication.SubscriptionScenarios
import utils.auth.OAuthAPI.config.{onceUsers, rampUpUsers, rampUpUsersDuration}

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class notifySubscriptionSimulation200Cases extends Simulation {

  private val notifySubscriptionExec = SubscriptionScenarios.notifySubscriptionTwoHundredCasesScenario
    .inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during (rampUpUsersDuration seconds)
    )

  setUp(notifySubscriptionExec)
    .protocols(httpProtocol)
    .assertions(
      details("Notify Subscription 200 Cases request").responseTime.percentile(90).lt(2000)
    )
    .assertions(
      details("Notify Subscription 200 Cases request").responseTime.percentile(95).lt(2500)
    )
    .assertions(
      details("Notify Subscription 200 Cases request").responseTime.percentile(99).lt(3000)
    )
}


