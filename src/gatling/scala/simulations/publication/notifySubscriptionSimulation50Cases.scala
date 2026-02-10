package simulations.publication

import io.gatling.core.Predef._
import requests.publication.NotificationRequests.httpProtocol
import scenarios.publication.NotificationScenarios
import scenarios.data.PublicationScenarios
import utils.auth.OAuthAPI
import utils.auth.OAuthAPI.config.{onceUsers, rampUpUsers, rampUpUsersDuration}
import utils.helpers.GeneralHelper

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class notifySubscriptionSimulation50Cases extends Simulation {

  private val artefactIdFile = "build/tmp/artefactIdFor50Cases.csv"
  val internalLocationId = 14 // Internal location_id of the court in the DB
  val externalLocationId = 539 // External location_id of the above court (links to provenance)

  private val createPublicationDifferentSizesExec = scenario("Create Publication Civil And Family request 50 cases")
    .exec(OAuthAPI.authData)
    .exec(PublicationScenarios.createFiftyCases(externalLocationId))
    .exec(session => GeneralHelper.saveArtefactId(artefactIdFile, session))

  private val notifySubscriptionExec = scenario("Notify Subscription 50 Cases")
    .exec(OAuthAPI.authPublication)
    .exec(session => session.set("artefactId", GeneralHelper.readFirstArtefactId(artefactIdFile)))
    .group("Notify for 50 cases")(
      exec(NotificationScenarios.notifySubscriptionFiftyCasesFlow("${artefactId}", internalLocationId))
    )

  setUp(
    createPublicationDifferentSizesExec.inject(atOnceUsers(onceUsers)).andThen(notifySubscriptionExec.inject(atOnceUsers(onceUsers), rampUsers(rampUpUsers) during rampUpUsersDuration.seconds))
  )
    .protocols(httpProtocol)
    .assertions(
      details("Notify for 50 cases" / "Notify Subscription").responseTime.percentile(90).lt(2000)
    )
    .assertions(
      details("Notify for 50 cases" / "Notify Subscription").responseTime.percentile(95).lt(2500)
    )
    .assertions(
      details("Notify for 50 cases" / "Notify Subscription").responseTime.percentile(99).lt(3000)
    )
}
