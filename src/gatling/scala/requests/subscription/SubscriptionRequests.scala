package requests.subscription

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder._
import utils.auth.OAuthAPI.config

import java.util.UUID

object SubscriptionRequests {

  // Set paths for endpoints
  private val CreateSubscriptionPath = config.accountManagementApi.url + "/subscription"
  private val ConfigureListTypePath = config.accountManagementApi.url + "/subscription/configure-list-types/"

  // Load court CSV file
  val locationListFeed = csv("courtLists/ReferenceData.csv").circular

  val userId = config.testVerifiedUserId
  val createSubscriptionByLocation = s"""{"channel":"EMAIL","searchType":"LOCATION_ID","searchValue":"996","userId":"$userId","locationName":"This is the location name","lastUpdatedDate":"2024-12-01T01:01:01.123456Z"}"""
  val createSubscriptionByCaseName = s"""{"channel":"EMAIL","searchType":"CASE_ID","searchValue":"${"$"}{randomName}","userId":"$userId","caseName":"TestCaseName","lastUpdatedDate":"2024-12-01T01:01:01.123456Z"}"""
  val createSubscriptionByUrn = s"""{"channel":"EMAIL","searchType":"CASE_URN","searchValue":"${"$"}{randomName}","userId":"$userId","caseName":"TestCaseName","lastUpdatedDate":"2024-12-01T01:01:01.123456Z"}"""
  val configureListType = s"""{"userId":"$userId","listType":["CIVIL_AND_FAMILY_DAILY_CAUSE_LIST","CIVIL_DAILY_CAUSE_LIST","FAMILY_DAILY_CAUSE_LIST"],"listLanguage":["ENGLISH"]}"""

  val httpProtocol = http.baseUrl(config.accountManagementApi.url)

  val postCreateSubscriptionByLocationRequest: HttpRequestBuilder = http("Create Subscription By Location Request")
    .post(CreateSubscriptionPath)
    .body(StringBody(createSubscriptionByLocation)).asJson
    .header("Authorization", "bearer ${AuthTokenAccountManagement}")
    .header("Accept", "application/json")
    .header("x-user-id", userId)
    .header("x-requester-id", userId)
    .check(status is 201)

  val postCreateSubscriptionByCaseNameRequest: HttpRequestBuilder = http("Create Subscription By CaseName Request")
    .post(CreateSubscriptionPath)
    .body(StringBody(createSubscriptionByCaseName)).asJson
    .header("Authorization", "bearer ${AuthTokenAccountManagement}")
    .header("Accept", "application/json")
    .header("x-user-id", userId)
    .header("x-requester-id", userId)
    .check(status is 201)

  val postCreateSubscriptionByCaseUrnRequest: HttpRequestBuilder = http("Create Subscription By CaseUrn Request")
    .post(CreateSubscriptionPath)
    .body(StringBody(createSubscriptionByUrn)).asJson
    .header("Authorization", "bearer ${AuthTokenAccountManagement}")
    .header("Accept", "application/json")
    .header("x-user-id", userId)
    .header("x-requester-id", userId)
    .check(status is 201)

  val putConfigureListTypeRequest: HttpRequestBuilder = http("Configure List Type For Subscription")
    .put(ConfigureListTypePath + userId)
    .body(StringBody(configureListType)).asJson
    .header("Authorization", "bearer ${AuthTokenAccountManagement}")
    .header("Accept", "application/json")
    .header("x-user-id", userId)
    .header("x-requester-id", userId)
    .check(status is 200)

}

