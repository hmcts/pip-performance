package simulations.frontEnd

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._
import scenarios.frontEnd.SignInScenarios
import utils.auth.OAuthAPI.config.{onceUsers, rampUpUsers, rampUpUsersDuration}

class SignInProcessSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("https://pip-frontend.test.platform.hmcts.net")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate, br")
    .acceptLanguageHeader("en-GB,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
    .silentResources
    .inferHtmlResources()

  val loginScenario = scenario("Common Platform Login")
    .exec(SignInScenarios.commonPlatformLogin)
    .exec(SignInScenarios.logout)


  // Simulation Setup
  setUp(
    loginScenario.inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during (rampUpUsersDuration seconds)
    ).protocols(httpProtocol)
  ).assertions(
    global.responseTime.max.lt(10000),
    global.successfulRequests.percent.gt(95)
  )
}
