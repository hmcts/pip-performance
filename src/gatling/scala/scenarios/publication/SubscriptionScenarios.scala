package scenarios.publication

import io.gatling.core.Predef._
import requests.publication.SubscriptionRequests
import utils.auth.OAuthAPI

object SubscriptionScenarios {

  val notifySubscriptionTwoCasesScenario = scenario("Generate PDF 2 cases")
    .exec(OAuthAPI.authPublication)
    .exec(SubscriptionRequests.postNotifySubscriptionTwoCasesRequest)

  val notifySubscriptionFiftyCasesScenario = scenario("Generate PDF 50 cases")
    .exec(OAuthAPI.authPublication)
    .exec(SubscriptionRequests.postNotifySubscriptionFiftyCasesRequest)

  val notifySubscriptionHundredCasesScenario = scenario("Generate PDF 100 cases")
    .exec(OAuthAPI.authPublication)
    .exec(SubscriptionRequests.postNotifySubscriptionHundredCasesRequest)

  val notifySubscriptionTwoHundredCasesScenario = scenario("Generate PDF 200 cases")
    .exec(OAuthAPI.authPublication)
    .exec(SubscriptionRequests.postNotifySubscriptionTwoHundredCasesRequest)
}
