package scenarios.account

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import requests.account.AccountRequests
import requests.subscription.SubscriptionRequests
import scala.collection.mutable.ListBuffer

object AccountScenarios {

  private val TestEmailPrefix = "pip-local-perf-test-email-prefix-"
  val createSubscriptionByLocationWithUser = "{\"channel\":\"EMAIL\",\"searchType\":\"LOCATION_ID\",\"searchValue\":\"${locationId}\",\"userId\":\"${userId}\",\"locationName\":\"This is the location name\",\"lastUpdatedDate\":\"2024-12-01T01:01:01.123456Z\"}"
  val configureListTypeWithuser = "{\"userId\": \"${userId}\",\"listType\":${listTypes},\"listLanguage\":[\"ENGLISH\"]}"

  def createAccountAndSubscriptionByLocationAndListType(usersToCreate: Int, internalLocationId: Int, listTypesJson: String): ChainBuilder = {
    val sequenceRangeBatch1 = Seq.range(1, usersToCreate / 2, 1)
    val sequenceRangeBatch2 = Seq.range(usersToCreate / 2, usersToCreate + 1, 1)

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


    exec(AccountRequests.createAccount(accountsBatch1))
      .exec(session => session.set("locationId", internalLocationId))
      .exec(session => session.set("listTypes", listTypesJson))
      .exec(session => {
        println(session("ResponseBody").as[String])
        session
      })
      .foreach("#{createdAccountIds}", "userId")(
        exec(SubscriptionRequests.postCreateSubscriptionByLocationRequest("#{userId}", createSubscriptionByLocationWithUser))
          .exec(SubscriptionRequests.putConfigureListTypeRequest("#{userId}", configureListTypeWithuser))
      )
      .exec(AccountRequests.createAccount(accountsBatch2))
      .foreach("#{createdAccountIds}", "userId")(
        exec(SubscriptionRequests.postCreateSubscriptionByLocationRequest("#{userId}", createSubscriptionByLocationWithUser))
          .exec(SubscriptionRequests.putConfigureListTypeRequest("#{userId}", configureListTypeWithuser))
      )
  }


  val deleteTestAccountsFlow: ChainBuilder =
    exec(AccountRequests.deleteAccounts(TestEmailPrefix))



}
