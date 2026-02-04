package simulations.combined

import io.gatling.core.Predef._
import requests.publication.NotificationRequests.httpProtocol
import scenarios.account.AccountScenarios
import scenarios.data.PublicationScenarios
import utils.auth.OAuthAPI
import utils.auth.OAuthAPI.config.{rampUpUsers, rampUpUsersDuration}

import scala.concurrent.duration._

class PublicationFlowSimulation extends Simulation {

  val numberOfUsers = 2 // The number of users that should be created
  val internalLocationId = 14 // Internal location_id of the court in the DB
  val externalLocationId = 539 // External location_id of the above court (links to provenance)
  val numberOfPublications = 5 // Number of publications to create, equally split between civil and family, and PDDA
  val listTypes = Array("CIVIL_AND_FAMILY_DAILY_CAUSE_LIST", "CROWN_FIRM_PDDA_LIST") // List types all users should be subscribed to

  def listTypesJson: String = listTypes.map(s => s""""$s"""").mkString("[", ",", "]")

  private val setupScenario = scenario("Setup Auth and Account")
    .exec(OAuthAPI.authAccount)
    .exec(AccountScenarios.createAccountAndSubscriptionByLocationAndListType(numberOfUsers, internalLocationId, listTypesJson))

  private val publicationsScenario = scenario("Send Civil and Family, and PDDA Firm List publication")
    .exec(OAuthAPI.authData)
    .randomSwitch(
      50.0 -> PublicationScenarios.createPublicationCivilAndFamilyOnly(externalLocationId),
      50.0 -> PublicationScenarios.createPublicationCrownFirmPdda(externalLocationId)
    )

  private val deleteAccountScenario = scenario("Delete Test Accounts")
    .exec(OAuthAPI.authAccount)
    .exec(AccountScenarios.deleteTestAccountsFlow)

  setUp(
    setupScenario.inject(atOnceUsers(1)),
    publicationsScenario.inject(atOnceUsers(numberOfPublications), rampUsers(rampUpUsers) during (rampUpUsersDuration seconds)),
    deleteAccountScenario.inject(atOnceUsers(1))
  )
    .protocols(httpProtocol)
    .assertions(
      Seq(
        details("Create a PI_AAD Account").responseTime.percentile(90).lt(2000),
        details("Create Publication Civil And Family request").responseTime.percentile(90).lt(1000),
        details("Create Publication Crown Firm Pdda request").responseTime.percentile(90).lt(1000),
        details("Create Publication Civil And Family request").responseTime.percentile(95).lt(1500),
        details("Create Publication Crown Firm Pdda request").responseTime.percentile(95).lt(1000),
        details("Create Publication Civil And Family request").responseTime.percentile(99).lt(2000),
        details("Create Publication Crown Firm Pdda request").responseTime.percentile(99).lt(1000),
        details("Configure List Type For Subscription").responseTime.percentile(90).lt(2000),
        details("Configure List Type For Subscription").responseTime.percentile(95).lt(2500),
        details("Configure List Type For Subscription").responseTime.percentile(99).lt(3000)
      ): _*
    )
}