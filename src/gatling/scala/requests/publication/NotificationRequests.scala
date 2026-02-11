package requests.publication

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder._
import utils.auth.OAuthAPI.config

object NotificationRequests {

  // Set paths for endpoints
  private val NotifySubscriptionPath = "/notify/subscription"

  val httpProtocol = http.baseUrl(config.publicationServicesApi.url)

  def postNotifySubscription(requestBody: String): HttpRequestBuilder = http("Notify Subscription")
    .post(NotifySubscriptionPath)
    .body(StringBody(requestBody)).asJson
    .header("Authorization", "bearer ${AuthTokenPubServices}")
    .header("Accept", "application/json")
}

