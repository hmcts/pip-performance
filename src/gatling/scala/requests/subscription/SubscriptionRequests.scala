package requests.subscription

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder._
import utils.auth.OAuthAPI.config

import scala.util.Random

object SubscriptionRequests {

  // Set paths for endpoints
  private val CreateSubscriptionPath = "/subscription"
  private val ConfigureListTypePath = "/subscription/configure-list-types/"
  private val DeleteSubscriptionPath = "/subscription/user/"


  val userId = "6a2b020b-ac4f-4bb0-9dd6-58f9a3c226a1"
  val createSubscriptionByLocation = "{\"channel\":\"EMAIL\",\"searchType\":\"LOCATION_ID\",\"searchValue\":\"${randomNumber}\",\"userId\":\"0e68f98c-29c5-4eff-aa26-0a872ee8bf86\",\"locationName\":\"locationName\",\"lastUpdatedDate\":\"2024-12-01T01:01:01.123456Z\"}"
  val createSubscriptionByCaseName = "{\"channel\":\"EMAIL\",\"searchType\":\"CASE_ID\",\"searchValue\":\"${randomName}\",\"userId\":\"0e68f98c-29c5-4eff-aa26-0a872ee8bf86\",\"caseName\":\"TestCaseName\",\"lastUpdatedDate\":\"2024-12-01T01:01:01.123456Z\"}"
  val createSubscriptionByUrn = "{\"channel\":\"EMAIL\",\"searchType\":\"CASE_URN\",\"searchValue\":\"${randomName}\",\"userId\":\"0e68f98c-29c5-4eff-aa26-0a872ee8bf86\",\"caseName\":\"TestCaseName\",\"lastUpdatedDate\":\"2024-12-01T01:01:01.123456Z\"}"
  val configureListType = "{\"userId\":\"0e68f98c-29c5-4eff-aa26-0a872ee8bf86\",\"listType\":[\"RPT_MIDLANDS_WEEKLY_HEARING_LIST\",\"RPT_NORTHERN_WEEKLY_HEARING_LIST\"],\"listLanguage\":[\"ENGLISH\"]}"

  val httpProtocol = http.baseUrl(config.subscriptionManagementApi.url)

  val postCreateSubscriptionByLocationRequest: HttpRequestBuilder = http("Create Subscription By Location Request")
    .post(CreateSubscriptionPath)
    .body(StringBody(createSubscriptionByLocation)).asJson
    .header("Authorization", "bearer ${bearerx}")
    .header("Accept", "application/json")
    .header("x-user-id", userId)
    .check(status is 201)

  val postCreateSubscriptionByCaseNameRequest: HttpRequestBuilder = http("Create Subscription By CaseName Request")
    .post(CreateSubscriptionPath)
    .body(StringBody(createSubscriptionByCaseName)).asJson
    .header("Authorization", "bearer ${bearerx}")
    .header("Accept", "application/json")
    .header("x-user-id", userId)
    .check(status is 201)

  val postCreateSubscriptionByCaseUrnRequest: HttpRequestBuilder = http("Create Subscription By CaseUrn Request")
    .post(CreateSubscriptionPath)
    .body(StringBody(createSubscriptionByUrn)).asJson
    .header("Authorization", "bearer ${bearerx}")
    .header("Accept", "application/json")
    .header("x-user-id", userId)
    .check(status is 201)

  val putConfigureListTypeRequest: HttpRequestBuilder = http("Configure List Type For Subscription")
    .put(ConfigureListTypePath + userId)
    .body(StringBody(configureListType)).asJson
    .header("Authorization", "bearer ${bearerx}")
    .header("Accept", "application/json")
    .header("x-user-id", userId)
    .check(status is 200)

  val deleteSubscriptionByUserIdRequest: HttpRequestBuilder = http("Delete Subscription By User Id")
    .delete(DeleteSubscriptionPath + userId)
    .header("Authorization", "bearer ${bearerx}")
    .header("Accept", "application/json")
    .header("x-user-id", userId)
    .check(status is 200)
}

