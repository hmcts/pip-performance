package scenarios.subscription

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import requests.subscription.SubscriptionRequests
import requests.subscription.SubscriptionRequests.locationListFeed
import utils.auth.OAuthAPI.config

import scala.util.Random

object SubscriptionScenarios {

  val userId = config.testVerifiedUserId
  val createSubscriptionByLocation = s"""{"channel":"EMAIL","searchType":"LOCATION_ID","searchValue":"996","userId":"$userId","locationName":"This is the location name","lastUpdatedDate":"2024-12-01T01:01:01.123456Z"}"""
  val createSubscriptionByCaseName = s"""{"channel":"EMAIL","searchType":"CASE_ID","searchValue":"${"$"}{randomName}","userId":"$userId","caseName":"TestCaseName","lastUpdatedDate":"2024-12-01T01:01:01.123456Z"}"""
  val createSubscriptionByUrn = s"""{"channel":"EMAIL","searchType":"CASE_URN","searchValue":"${"$"}{randomName}","userId":"$userId","caseName":"TestCaseName","lastUpdatedDate":"2024-12-01T01:01:01.123456Z"}"""
  val configureListType = s"""{"userId":"$userId","listType":["CIVIL_AND_FAMILY_DAILY_CAUSE_LIST","CIVIL_DAILY_CAUSE_LIST","FAMILY_DAILY_CAUSE_LIST"],"listLanguage":["ENGLISH"]}"""

  // Reusable random name feeder
  val feedRandomName = Iterator.continually {
    Map("randomName" -> s"${Random.alphanumeric.take(20).mkString}")
  }

  // Pure flows without authentication
  val createSubscriptionByLocationFlow: ChainBuilder =
    feed(locationListFeed)
      .exec(SubscriptionRequests.postCreateSubscriptionByLocationRequest(userId, createSubscriptionByLocation))

  val createSubscriptionByCaseNameFlow: ChainBuilder =
    feed(feedRandomName)
      .exec(SubscriptionRequests.postCreateSubscriptionByCaseNameRequest(userId, createSubscriptionByCaseName) )

  val createSubscriptionByCaseUrnFlow: ChainBuilder =
    feed(feedRandomName)
      .exec(SubscriptionRequests.postCreateSubscriptionByCaseUrnRequest(userId, createSubscriptionByUrn))

  val configureListTypeFlow: ChainBuilder =
    exec(SubscriptionRequests.putConfigureListTypeRequest(userId, configureListType))
}
