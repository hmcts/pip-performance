package requests.publication

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder._
import utils.auth.OAuthAPI.config

object SubscriptionRequests {

  // Set paths for endpoints
  private val NotifySubscriptionPath = "/notify/subscription"
  val notifySubscriptionBodyTwoCases = "{\"email\":\"test_account_admin@justice.gov.uk\",\"subscriptions\": {\"LOCATION_ID\":[\"996\"]}, \"artefactId\": \"017e9e51-f970-475e-896a-164c70f4e60e\"}"
  val notifySubscriptionBodyFiftyCases = "{\"email\":\"test_account_admin@justice.gov.uk\",\"subscriptions\": {\"LOCATION_ID\":[\"996\"]}, \"artefactId\": \"f0f2446c-82e6-4154-bf78-b72f4278229d\"}"
  val notifySubscriptionBodyHundredCases = "{\"email\":\"test_account_admin@justice.gov.uk\",\"subscriptions\": {\"LOCATION_ID\":[\"996\"]}, \"artefactId\": \"e6f5b986-5659-40f0-af3a-6e947bf155c2\"}"
  val notifySubscriptionBodyTwoHundredCases = "{\"email\":\"test_account_admin@justice.gov.uk\",\"subscriptions\": {\"LOCATION_ID\":[\"996\"]}, \"artefactId\": \"88f38043-cd71-4843-a62a-6daf16f4c7f5\"}"

  val httpProtocol = http.baseUrl(config.publicationServicesApi.url)

  val postNotifySubscriptionTwoCasesRequest: HttpRequestBuilder = http("Notify Subscription 2 Cases request")
    .post(NotifySubscriptionPath)
    .body(StringBody(notifySubscriptionBodyTwoCases)).asJson
    .header("Authorization", "bearer ${bearerx}")
    .header("Accept", "application/json")
    .check(status is 200)

  val postNotifySubscriptionFiftyCasesRequest: HttpRequestBuilder = http("Notify Subscription 50 Cases request")
    .post(NotifySubscriptionPath)
    .body(StringBody(notifySubscriptionBodyFiftyCases)).asJson
    .header("Authorization", "bearer ${bearerx}")
    .header("Accept", "application/json")
    .check(status is 200)

  val postNotifySubscriptionHundredCasesRequest: HttpRequestBuilder = http("Notify Subscription 100 Cases request")
    .post(NotifySubscriptionPath)
    .body(StringBody(notifySubscriptionBodyHundredCases)).asJson
    .header("Authorization", "bearer ${bearerx}")
    .header("Accept", "application/json")
    .check(status is 200)

  val postNotifySubscriptionTwoHundredCasesRequest: HttpRequestBuilder = http("Notify Subscription 200 Cases request")
    .post(NotifySubscriptionPath)
    .body(StringBody(notifySubscriptionBodyTwoHundredCases)).asJson
    .header("Authorization", "bearer ${bearerx}")
    .header("Accept", "application/json")
    .check(status is 200)
}

