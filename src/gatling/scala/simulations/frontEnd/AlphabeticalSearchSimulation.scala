package simulations.frontEnd

import io.gatling.core.Predef.{details, _}
import requests.frontEnd.SearchRequests.httpProtocol
import scenarios.frontEnd.SearchScenarios
import utils.auth.OAuthAPI.config.{onceUsers, rampUpUsers, rampUpUsersDuration}

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class AlphabeticalSearchSimulation extends Simulation {

  private val alphabeticalSearchExec = SearchScenarios.AlphabeticalSearchScenario
    .inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during (rampUpUsersDuration seconds)
    )

  setUp(alphabeticalSearchExec)
    .protocols(httpProtocol)
    .assertions(
      details("Get Alphabetical Search request").responseTime.percentile(90).lt(2000)
    )
    .assertions(
      details("Get Alphabetical Search request").responseTime.percentile(95).lt(2500)
    )
    .assertions(
      details("Get Alphabetical Search request").responseTime.percentile(99).lt(2500)
    )
}


