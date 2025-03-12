package simulations.subscription

import io.gatling.core.Predef._
import requests.subscription.SubscriptionRequests.httpProtocol
import scenarios.subscription.SubscriptionScenarios
import utils.auth.OAuthAPI.config.{onceUsers, rampUpUsers, rampUpUsersDuration}

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class deleteSubscriptionByUserIdSimulation extends Simulation {

  private val deleteSubscriptionByUserIdExec = SubscriptionScenarios.deleteSubscriptionByUserIdScenario
    .inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during (rampUpUsersDuration seconds)
    )

  setUp(deleteSubscriptionByUserIdExec)
    .protocols(httpProtocol)
    .assertions(
      details("Delete Subscription By User Id").responseTime.percentile(90).lt(2000)
    )
    .assertions(
      details("Delete Subscription By User Id").responseTime.percentile(95).lt(2500)
    )
    .assertions(
      details("Delete Subscription By User Id").responseTime.percentile(99).lt(3000)
    )
}