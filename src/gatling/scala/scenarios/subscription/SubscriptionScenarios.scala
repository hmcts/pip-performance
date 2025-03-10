package scenarios.subscription

import io.gatling.core.Predef._
import requests.subscription.SubscriptionRequests
import utils.auth.OAuthAPI

object SubscriptionScenarios {
  val createSubscriptionByLocationScenario = scenario("Subscription By Location")
    .exec(OAuthAPI.authAccount)
    .exec(SubscriptionRequests.postCreateSubscriptionByLocationRequest)

  val createSubscriptionByCaseNameScenario = scenario("Subscription By Case Name")
    .exec(OAuthAPI.authAccount)
    .exec(SubscriptionRequests.postCreateSubscriptionByCaseNameRequest)


  val createSubscriptionByCaseUrnScenario = scenario("Subscription By Case Urn")
    .exec(OAuthAPI.authAccount)
    .exec(SubscriptionRequests.postCreateSubscriptionByCaseUrnRequest)

  val configureListTypeScenario = scenario("Configure List Type")
    .exec(OAuthAPI.authAccount)
    .exec(SubscriptionRequests.putConfigureListTypeRequest)

  val deleteSubscriptionByUserIdScenario = scenario("Delete Subscription by User Id")
    .exec(OAuthAPI.authAccount)
    .exec(SubscriptionRequests.deleteSubscriptionByUserIdRequest)
}
