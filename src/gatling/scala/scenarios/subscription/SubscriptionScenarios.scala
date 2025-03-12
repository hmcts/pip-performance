package scenarios.subscription

import io.gatling.core.Predef._
import requests.subscription.SubscriptionRequests
import requests.subscription.SubscriptionRequests.locationListFeed
import utils.auth.OAuthAPI

import scala.util.Random

object SubscriptionScenarios {

  val feedRandomName = Iterator.continually {
    Map("randomName" -> s"${Random.alphanumeric.take(20).mkString}")
  }

  val createSubscriptionByLocationScenario = scenario("Subscription By Location For Publication")
    .feed(locationListFeed)
    .exec(OAuthAPI.authSubscription)
    .exec(SubscriptionRequests.postCreateSubscriptionByLocationRequest)

  val createSubscriptionByCaseNameScenario = scenario("Subscription By Case Name")
    .exec(OAuthAPI.authSubscription)
    .feed(feedRandomName)
    .exec(SubscriptionRequests.postCreateSubscriptionByCaseNameRequest)

  val createSubscriptionByCaseUrnScenario = scenario("Subscription By Case Urn")
    .exec(OAuthAPI.authSubscription)
    .feed(feedRandomName)
    .exec(SubscriptionRequests.postCreateSubscriptionByCaseUrnRequest)

  val configureListTypeScenario = scenario("Configure List Type")
    .exec(OAuthAPI.authSubscription)
    .exec(SubscriptionRequests.putConfigureListTypeRequest)

  val deleteSubscriptionByUserIdScenario = scenario("Delete Subscription by User Id")
    .exec(OAuthAPI.authSubscription)
    .exec(SubscriptionRequests.deleteSubscriptionByUserIdRequest)
}
