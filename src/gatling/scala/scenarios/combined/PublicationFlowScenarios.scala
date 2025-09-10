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
  private val UsersToCreate = 1 //Used to determine how many users should be created and subscriptions sent.

  private val sequenceRangeBatch1 = Seq.range(1, UsersToCreate/2, 1)
  private val sequenceRangeBatch2 = Seq.range(UsersToCreate/2, UsersToCreate+1, 1)

  val accountsBatch1: ListBuffer[Map[String, String]] = ListBuffer()
  for (n <- sequenceRangeBatch1) {
    accountsBatch1.addOne(Map[String, String](
      "userProvenance" -> "CRIME_IDAM",
      "provenanceUserId" -> java.util.UUID.randomUUID.toString,
      "email" -> (TestEmailPrefix + n + "@justice.gov.uk"),
      "roles" -> "VERIFIED"
    ))
  }

  val accountsBatch2: ListBuffer[Map[String, String]] = ListBuffer()
  for (n <- sequenceRangeBatch2) {
    accountsBatch2.addOne(Map[String, String](
      "userProvenance" -> "CRIME_IDAM",
      "provenanceUserId" -> java.util.UUID.randomUUID.toString,
      "email" -> (TestEmailPrefix + n + "@justice.gov.uk"),
      "roles" -> "VERIFIED"
    ))
  }

  val testBulkSendSubscription = scenario("Generate Bulk Send Subscription")
    .exec(OAuthAPI.authAccount)
    .exec(AccountRequests.createAccount(accountsBatch1))
    .exec(session => {
      session.set("locationId", TestLocationid)
    })
    .exec(session => {
      println(session("ResponseBody").as[String])
      session})
    .foreach("#{createdAccountIds}", "userId") (
        exec(SubscriptionRequests.postCreateSubscriptionByLocationRequestWithUser)
          .exec(SubscriptionRequests.putConfigureListTypeRequestWithUser))

    .exec(AccountRequests.createAccount(accountsBatch2))
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

    .pause(30)
    .exec(PublicationRequests.createPublicationCivilAndFamilyRequest)
    .pause(30)
    .exec(PublicationRequests.createPublicationCivilAndFamilyRequest)
    .pause(30)
    .exec(PublicationRequests.createPublicationCivilAndFamilyRequest)
    .pause(30)
    .exec(PublicationRequests.createPublicationCivilAndFamilyRequest)
    .pause(30)
    .exec(PublicationRequests.createPublicationCivilAndFamilyRequest)
    .pause(30)
    .exec(PublicationRequests.createPublicationCivilAndFamilyRequest)
    .pause(30)
    .exec(PublicationRequests.createPublicationCivilAndFamilyRequest)
    .pause(30)
    .exec(PublicationRequests.createPublicationCivilAndFamilyRequest)
    .pause(30)
    .exec(PublicationRequests.createPublicationCivilAndFamilyRequest)


    .exec(OAuthAPI.authAccount)
    .exec(AccountRequests.deleteAccounts(TestEmailPrefix))

}