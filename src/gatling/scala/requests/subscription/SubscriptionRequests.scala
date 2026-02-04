package requests.subscription

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder._
import utils.auth.OAuthAPI.config

object SubscriptionRequests {

  // Set paths for endpoints
  private val CreateSubscriptionPath = config.accountManagementApi.url + "/subscription"
  private val ConfigureListTypePath = config.accountManagementApi.url + "/subscription/configure-list-types/"

  // Load court CSV file
  val locationListFeed = csv("courtLists/ReferenceData.csv").circular
  val httpProtocol = http.baseUrl(config.accountManagementApi.url)

  def postCreateSubscriptionByLocationRequest(userId: String, body: String): HttpRequestBuilder = http("Create Subscription By Location Request")
    .post(CreateSubscriptionPath)
    .body(StringBody(body)).asJson
    .header("Authorization", "bearer ${AuthTokenAccountManagement}")
    .header("Accept", "application/json")
    .header("x-user-id", userId)
    .header("x-requester-id", userId)
    .check(status is 201)

  def postCreateSubscriptionByCaseNameRequest(userId: String, body: String): HttpRequestBuilder = http("Create Subscription By CaseName Request")
    .post(CreateSubscriptionPath)
    .body(StringBody(body)).asJson
    .header("Authorization", "bearer ${AuthTokenAccountManagement}")
    .header("Accept", "application/json")
    .header("x-user-id", userId)
    .header("x-requester-id", userId)
    .check(status is 201)

  def postCreateSubscriptionByCaseUrnRequest(userId: String, body: String): HttpRequestBuilder = http("Create Subscription By CaseUrn Request")
    .post(CreateSubscriptionPath)
    .body(StringBody(body)).asJson
    .header("Authorization", "bearer ${AuthTokenAccountManagement}")
    .header("Accept", "application/json")
    .header("x-user-id", userId)
    .header("x-requester-id", userId)
    .check(status is 201)

  def putConfigureListTypeRequest(userId: String, body: String): HttpRequestBuilder = http("Configure List Type For Subscription")
    .put(ConfigureListTypePath + userId)
    .body(StringBody(body)).asJson
    .header("Authorization", "bearer ${AuthTokenAccountManagement}")
    .header("Accept", "application/json")
    .header("x-user-id", userId)
    .header("x-requester-id", userId)
    .check(status is 200)

}

