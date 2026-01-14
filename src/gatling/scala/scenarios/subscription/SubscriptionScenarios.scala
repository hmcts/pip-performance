package scenarios.subscription

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import requests.subscription.SubscriptionRequests
import requests.subscription.SubscriptionRequests.locationListFeed
import utils.auth.OAuthAPI.config

import scala.util.Random

object SubscriptionScenarios {

  // Reusable random name feeder
  val feedRandomName = Iterator.continually {
    Map("randomName" -> s"${Random.alphanumeric.take(20).mkString}")
  }

  // Pure flows without authentication
  val createSubscriptionByLocationFlow: ChainBuilder =
    feed(locationListFeed)
      .exec(SubscriptionRequests.postCreateSubscriptionByLocationRequest)

  val createSubscriptionByCaseNameFlow: ChainBuilder =
    feed(feedRandomName)
      .exec(SubscriptionRequests.postCreateSubscriptionByCaseNameRequest)

  val createSubscriptionByCaseUrnFlow: ChainBuilder =
    feed(feedRandomName)
      .exec(SubscriptionRequests.postCreateSubscriptionByCaseUrnRequest)

  val configureListTypeFlow: ChainBuilder =
    exec(SubscriptionRequests.putConfigureListTypeRequest)
}
