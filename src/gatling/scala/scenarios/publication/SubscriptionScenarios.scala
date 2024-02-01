package scenarios.publication

import io.gatling.core.Predef._
import requests.publication.SubscriptionRequests
import utils.auth.OAuthAPI

object SubscriptionScenarios {

  val notifySubscriptionScenario = scenario("Generate PDF")
    .exec(OAuthAPI.authPublication)
    .exec(SubscriptionRequests.postNotifySubscriptionTwoCasesRequest)
    .exec(SubscriptionRequests.postNotifySubscriptionFiftyCasesRequest)
    .exec(SubscriptionRequests.postNotifySubscriptionHundredCasesRequest)
    .exec(SubscriptionRequests.postNotifySubscriptionTwoHundredCasesRequest)
}
