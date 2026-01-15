package simulations.subscription

import io.gatling.core.Predef._
import requests.subscription.SubscriptionRequests.httpProtocol
import scenarios.subscription.SubscriptionScenarios
import utils.auth.OAuthAPI
import utils.auth.OAuthAPI.config.{onceUsers, rampUpUsers, rampUpUsersDuration}

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class createSubscriptionSimulationByCaseUrn extends Simulation {

  private val createSubscriptionByCaseUrnExec = scenario("Subscription By Case Urn with Auth")
    .exec(OAuthAPI.authAccount) // authenticate once per virtual user
    .exec(SubscriptionScenarios.createSubscriptionByCaseUrnFlow)

  setUp(
    createSubscriptionByCaseUrnExec.inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during rampUpUsersDuration.seconds
    )
  )
    .protocols(httpProtocol)
    .assertions(
      details("Create Subscription By CaseUrn Request").responseTime.percentile(90).lt(2000)
    )
    .assertions(
      details("Create Subscription By CaseUrn Request").responseTime.percentile(95).lt(2500)
    )
    .assertions(
      details("Create Subscription By CaseUrn Request").responseTime.percentile(99).lt(3000)
    )
}


