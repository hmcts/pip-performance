package scenarios.publication

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import requests.publication.SubscriptionRequests
import utils.auth.OAuthAPI.config

object SubscriptionScenarios {

  // Pure flows without authentication, now including requesterId
  val notifySubscriptionTwoCasesFlow: ChainBuilder =
    exec(SubscriptionRequests.postNotifySubscriptionTwoCasesRequest)

  val notifySubscriptionFiftyCasesFlow: ChainBuilder =
    exec(SubscriptionRequests.postNotifySubscriptionFiftyCasesRequest)

  val notifySubscriptionHundredCasesFlow: ChainBuilder =
    exec(SubscriptionRequests.postNotifySubscriptionHundredCasesRequest)

  val notifySubscriptionTwoHundredCasesFlow: ChainBuilder =
    exec(SubscriptionRequests.postNotifySubscriptionTwoHundredCasesRequest)
}
