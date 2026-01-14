package simulations.subscription

import io.gatling.core.Predef._
import requests.subscription.SubscriptionRequests.httpProtocol
import scenarios.subscription.SubscriptionScenarios
import utils.auth.OAuthAPI
import utils.auth.OAuthAPI.config.{onceUsers, rampUpUsers, rampUpUsersDuration}

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class configureListTypeSimulation extends Simulation {

  private val configureListTypesExec = scenario("Configure List Type For Subscription")
    .exec(OAuthAPI.authAccount)                           // authenticate once per virtual user
    .exec(SubscriptionScenarios.configureListTypeFlow)

  setUp(
    configureListTypesExec.inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during rampUpUsersDuration.seconds
    )
  )
    .protocols(httpProtocol)
    .assertions(
      details("Configure List Type For Subscription").responseTime.percentile(90).lt(2000)
    )
    .assertions(
      details("Configure List Type For Subscription").responseTime.percentile(95).lt(2500)
    )
    .assertions(
      details("Configure List Type For Subscription").responseTime.percentile(99).lt(3000)
    )
}


