package requests.publication

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder._
import utils.auth.OAuthAPI.config

object SubscriptionRequests {


  private val artefactIdTwoCases = "23aa4c86-4ffe-477b-b5e5-c76b8f3a9a12"
  private val artefactIdFiftyCases = "f5aed02c-b6b7-440e-9f66-32029bd823cc"
  private val artefactIdHundredCases = "90889d83-088a-4ebf-bf68-781962cb5d3e"
  private val artefactIdTwoHundredCases = "33538ac4-7cba-475d-b091-d7c0003dd2a3"

  // Set paths for endpoints
  private val NotifySubscriptionPath = "/notify/subscription"

  val notifySubscriptionBodyTwoCases = s"""{"artefactId":"$artefactIdTwoCases","subscriptionEmails":[{"email":"test_account_admin@justice.gov.uk","subscriptions":{"LOCATION_ID":["996"]}}]}"""
  val notifySubscriptionBodyFiftyCases = s"""{"artefactId":"$artefactIdFiftyCases","subscriptionEmails":[{"email":"test_account_admin@justice.gov.uk","subscriptions":{"LOCATION_ID":["996"]}}]}"""
  val notifySubscriptionBodyHundredCases = s"""{"artefactId":"$artefactIdHundredCases","subscriptionEmails":[{"email":"test_account_admin@justice.gov.uk","subscriptions":{"LOCATION_ID":["996"]}}]}"""
  val notifySubscriptionBodyTwoHundredCases = s"""{"artefactId":"$artefactIdTwoHundredCases","subscriptionEmails":[{"email":"test_account_admin@justice.gov.uk","subscriptions":{"LOCATION_ID":["996"]}}]}"""

  val httpProtocol = http.baseUrl(config.publicationServicesApi.url)

  val postNotifySubscriptionTwoCasesRequest: HttpRequestBuilder = http("Notify Subscription 2 Cases request")
    .post(NotifySubscriptionPath)
    .body(StringBody(notifySubscriptionBodyTwoCases)).asJson
    .header("Authorization", "bearer ${AuthTokenPubServices}")
    .header("Accept", "application/json")
    .check(status is 202)

  val postNotifySubscriptionFiftyCasesRequest: HttpRequestBuilder = http("Notify Subscription 50 Cases request")
    .post(NotifySubscriptionPath)
    .body(StringBody(notifySubscriptionBodyFiftyCases)).asJson
    .header("Authorization", "bearer ${AuthTokenPubServices}")
    .header("Accept", "application/json")
    .check(status is 202)

  val postNotifySubscriptionHundredCasesRequest: HttpRequestBuilder = http("Notify Subscription 100 Cases request")
    .post(NotifySubscriptionPath)
    .body(StringBody(notifySubscriptionBodyHundredCases)).asJson
    .header("Authorization", "bearer ${AuthTokenPubServices}")
    .header("Accept", "application/json")
    .check(status is 202)

  val postNotifySubscriptionTwoHundredCasesRequest: HttpRequestBuilder = http("Notify Subscription 200 Cases request")
    .post(NotifySubscriptionPath)
    .body(StringBody(notifySubscriptionBodyTwoHundredCases)).asJson
    .header("Authorization", "bearer ${AuthTokenPubServices}")
    .header("Accept", "application/json")
    .check(status is 202)
}

