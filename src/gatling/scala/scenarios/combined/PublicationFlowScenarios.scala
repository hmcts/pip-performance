package scenarios.combined

import io.gatling.core.Predef._
import requests.account.AccountRequests
import requests.data.PublicationRequests
import requests.subscription.SubscriptionRequests
import utils.auth.OAuthAPI

import java.time.LocalDateTime
import scala.collection.mutable.ListBuffer

object PublicationFlowScenarios {

  private val TestEmailPrefix = "pip-local-perf-test-email-prefix-"
  private val TestLocationid = 14 //To be moved to environment variable. Used for the court to perform the test for
  private val UsersToCreate = 10 //Used to determine how many users should be created and subscriptions sent.

  private val sequenceRange = Seq.range(1, UsersToCreate, 1)
  val accounts: ListBuffer[Map[String, String]] = ListBuffer()
  for (n <- sequenceRange) {
    accounts.addOne(Map[String, String](
      "userProvenance" -> "CRIME_IDAM",
      "provenanceUserId" -> java.util.UUID.randomUUID.toString,
      "email" -> (TestEmailPrefix + n + "@justice.gov.uk"),
      "roles" -> "VERIFIED"
    ))
  }

  val testBulkSendSubscription = scenario("Generate Bulk Send Subscription")
    .exec(OAuthAPI.authAccount)
    .exec(AccountRequests.createAccount(accounts))
    .exec(session => {
      session.set("locationId", TestLocationid)
    })
    .foreach("#{createdAccountIds}", "userId") (
        exec(SubscriptionRequests.postCreateSubscriptionByLocationRequestWithUser)
          .exec(SubscriptionRequests.putConfigureListTypeRequestWithUser))

    .exec(session => {
      session.set("startDate", LocalDateTime.now())
        .set("endDate", LocalDateTime.now().plusDays(1))
        .set("P&I ID", TestLocationid)
    })
    .exec(OAuthAPI.authData)
    .exec(PublicationRequests.createPublicationCivilAndFamilyRequest)
//    .pause(20)
//    .exec(PublicationRequests.createPublicationCivilAndFamilyRequest)
    .exec(OAuthAPI.authAccount)
    .exec(AccountRequests.deleteAccounts(TestEmailPrefix))

}