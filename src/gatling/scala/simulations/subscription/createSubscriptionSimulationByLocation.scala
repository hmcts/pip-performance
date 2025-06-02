package simulations.subscription

import io.gatling.core.Predef._
import requests.subscription.SubscriptionRequests.httpProtocol
import scenarios.subscription.SubscriptionScenarios
import utils.auth.OAuthAPI.config.{onceUsers, rampUpUsers, rampUpUsersDuration}

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class createSubscriptionSimulationByLocation extends Simulation {

  private val createSubscriptionByLocationExec = SubscriptionScenarios.createSubscriptionByLocationScenario
    .inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during (rampUpUsersDuration seconds)
    )

  setUp(createSubscriptionByLocationExec)
    .protocols(httpProtocol)
    .assertions(
      details("Create Subscription By Location Request").responseTime.percentile(90).lt(2000)
    )
    .assertions(
      details("Create Subscription By Location Request").responseTime.percentile(95).lt(2500)
    )
    .assertions(
      details("Create Subscription By Location Request").responseTime.percentile(99).lt(3000)
    )
}


