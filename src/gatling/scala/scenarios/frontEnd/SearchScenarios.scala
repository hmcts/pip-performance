package scenarios.frontEnd

import io.gatling.core.Predef._
import requests.frontEnd.SearchRequests

object SearchScenarios {

  val AlphabeticalSearchScenario = scenario("Alphabetical Search Post")
    .exec(SearchRequests.getAlphabeticalSearchRequest)
}
