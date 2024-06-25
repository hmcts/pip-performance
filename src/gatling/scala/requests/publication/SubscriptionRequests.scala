package requests.publication

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder._
import utils.auth.OAuthAPI.config

object SubscriptionRequests {





  // Set paths for endpoints
  private val NotifySubscriptionPath = "/notify/v2/subscription"

  val notifySubscriptionBodyTwoCases = "{\"artefactId\":\"2fcbf066-4657-43c9-b9f1-8313f649820f\",\"subscriptionEmails\": [{ \"email\": \"test_account_admin@justice.gov.uk\",\"subscriptions\": {\"LOCATION_ID\":[\"996\"]}}]}"
  val notifySubscriptionBodyFiftyCases = "{\"artefactId\":\"74a21307-48f4-43e4-a2fc-25091d46613c\",\"subscriptionEmails\": [{ \"email\": \"test_account_admin@justice.gov.uk\",\"subscriptions\": {\"LOCATION_ID\":[\"996\"]}}]}"
  val notifySubscriptionBodyHundredCases = "{\"artefactId\":\"506c4973-0283-46a5-9c90-eaa76e029ea8\",\"subscriptionEmails\": [{ \"email\": \"test_account_admin@justice.gov.uk\",\"subscriptions\": {\"LOCATION_ID\":[\"996\"]}}]}"
  val notifySubscriptionBodyTwoHundredCases = "{\"artefactId\":\"0e74e020-a7b8-49a1-b141-59a944da7e8e\",\"subscriptionEmails\": [{ \"email\": \"test_account_admin@justice.gov.uk\",\"subscriptions\": {\"LOCATION_ID\":[\"996\"]}}]}"

  val httpProtocol = http.baseUrl(config.publicationServicesApi.url)

  val postNotifySubscriptionTwoCasesRequest: HttpRequestBuilder = http("Notify Subscription 2 Cases request")
    .post(NotifySubscriptionPath)
    .body(StringBody(notifySubscriptionBodyTwoCases)).asJson
    .header("Authorization", "bearer ${bearerx}")
    .header("Accept", "application/json")
    .check(status is 202)

  val postNotifySubscriptionFiftyCasesRequest: HttpRequestBuilder = http("Notify Subscription 50 Cases request")
    .post(NotifySubscriptionPath)
    .body(StringBody(notifySubscriptionBodyFiftyCases)).asJson
    .header("Authorization", "bearer ${bearerx}")
    .header("Accept", "application/json")
    .check(status is 202)

  val postNotifySubscriptionHundredCasesRequest: HttpRequestBuilder = http("Notify Subscription 100 Cases request")
    .post(NotifySubscriptionPath)
    .body(StringBody(notifySubscriptionBodyHundredCases)).asJson
    .header("Authorization", "bearer ${bearerx}")
    .header("Accept", "application/json")
    .check(status is 202)

  val postNotifySubscriptionTwoHundredCasesRequest: HttpRequestBuilder = http("Notify Subscription 200 Cases request")
    .post(NotifySubscriptionPath)
    .body(StringBody(notifySubscriptionBodyTwoHundredCases)).asJson
    .header("Authorization", "bearer ${bearerx}")
    .header("Accept", "application/json")
    .check(status is 202)
}

