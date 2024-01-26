package requests.frontEnd

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder._
import utils.auth.OAuthAPI.config

object SearchRequests {

  // Set paths for endpoints
  private val SearchAlphabeticalPath = "/alphabetical-search"

  val httpProtocol = http.baseUrl(config.frontEndUrl)

  val getAlphabeticalSearchRequest: HttpRequestBuilder = http("Get Alphabetical Search request")
    .get(SearchAlphabeticalPath)
    .check(status is 200)
}
