package simulations.publication

import io.gatling.core.Predef._
import requests.publication.NotificationRequests.httpProtocol
import scenarios.publication.NotificationScenarios
import scenarios.data.PublicationScenarios
import utils.auth.OAuthAPI
import utils.auth.OAuthAPI.config.{onceUsers, rampUpUsers, rampUpUsersDuration}
import utils.helpers.GeneralHelper

import scala.concurrent.duration.DurationInt
import scala.language.{existentials, postfixOps}

class notifySubscriptionSimulation100Cases extends Simulation {

  private val artefactIdFile = "build/tmp/artefactIdFor100Cases.csv"
  val internalLocationId = 14 // Internal location_id of the court in the DB
  val externalLocationId = 539 // External location_id of the above court (links to provenance)

  private val createPublicationDifferentSizesExec = scenario("Create Publication Civil And Family request 100 cases")
    .exec(OAuthAPI.authData)
    .exec(PublicationScenarios.createHundredCases(externalLocationId))
    .exec(session => GeneralHelper.saveArtefactId(artefactIdFile, session))

  private val notifySubscriptionExec = scenario("Notify Subscription 100 Cases")
    .exec(OAuthAPI.authPublication)
    .exec(session => session.set("artefactId", GeneralHelper.readFirstArtefactId(artefactIdFile)))
    .exec(NotificationScenarios.notifySubscriptionHundredCasesFlow("${artefactId}", internalLocationId));

  setUp(
    createPublicationDifferentSizesExec.inject(atOnceUsers(onceUsers)).andThen(
    notifySubscriptionExec.inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during rampUpUsersDuration.seconds
    ))
  )
    .protocols(httpProtocol)
    .assertions(
      details("Notify Subscription").responseTime.percentile(90).lt(2000)
    )
    .assertions(
      details("Notify Subscription").responseTime.percentile(95).lt(2500)
    )
    .assertions(
      details("Notify Subscription").responseTime.percentile(99).lt(3000)
    )
}
