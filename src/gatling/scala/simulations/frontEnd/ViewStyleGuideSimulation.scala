package simulations.frontEnd

import io.gatling.core.Predef.{details, _}
import requests.frontEnd.SearchRequests.httpProtocol
import scenarios.frontEnd.StyleGuideScenarios
import scenarios.frontEnd.StyleGuideScenarios.createStyleGuideScenario
import utils.auth.OAuthAPI.config.{rampUpUsers, rampUpUsersDuration}

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class ViewStyleGuideSimulation extends Simulation {

  setUp(
    //Create publication ONCE
    createStyleGuideScenario.inject(
      atOnceUsers(1)
    ),

    //View the SAME publication many times
    StyleGuideScenarios.viewStyleGuideScenario.inject(
      nothingFor(10 seconds),
      rampUsers(rampUpUsers) during (rampUpUsersDuration seconds)
    )
  )
    .protocols(httpProtocol)
    .assertions(
      details("View Civil And Family Daily Cause List").responseTime.percentile(90).lt(2000),
      details("View Civil And Family Daily Cause List").responseTime.percentile(95).lt(2500),
      details("View Civil And Family Daily Cause List").responseTime.percentile(99).lt(2500)
    )
}