package requests.combined

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder._
import utils.auth.OAuthAPI.config

object SubscriptionRequests {

  // Set paths for endpoints
  private val CreateSubscriptionPath = config.accountManagementApi.url + "/subscription"
  private val ConfigureListTypePath = config.accountManagementApi.url + "/subscription/configure-list-types/"

  val createSubscriptionByLocationWithUser = "{\"channel\":\"EMAIL\",\"searchType\":\"LOCATION_ID\",\"searchValue\":\"${locationId}\",\"userId\":\"${userId}\",\"locationName\":\"This is the location name\",\"lastUpdatedDate\":\"2024-12-01T01:01:01.123456Z\"}"
  val configureListTypeWithuser = "{\"userId\": \"${userId}\",\"listType\":[\"CIVIL_AND_FAMILY_DAILY_CAUSE_LIST\",\"CIVIL_DAILY_CAUSE_LIST\",\"FAMILY_DAILY_CAUSE_LIST\"],\"listLanguage\":[\"ENGLISH\"]}"

  val httpProtocol = http.baseUrl(config.accountManagementApi.url)

  val postCreateSubscriptionByLocationRequestWithUser: HttpRequestBuilder = http("Create Subscription By Location Request for a User")
    .post(CreateSubscriptionPath)
    .body(StringBody(createSubscriptionByLocationWithUser)).asJson
    .header("Authorization", "bearer ${AuthTokenAccountManagement}")
    .header("Accept", "application/json")
    .header("x-user-id", "${userId}")
    .header("x-requester-id", "${userId}")
    .check(status is 201)

  val putConfigureListTypeRequestWithUser: HttpRequestBuilder = http("Configure List Type For Subscription")
    .put(ConfigureListTypePath + "${userId}")
    .body(StringBody(configureListTypeWithuser)).asJson
    .header("Authorization", "bearer ${AuthTokenAccountManagement}")
    .header("Accept", "application/json")
    .header("x-user-id", "${userId}")
    .header("x-requester-id", "${userId}")
    .check(status is 200)

}

