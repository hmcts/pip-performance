package simulations.data

import io.gatling.core.Predef.{details, _}
import requests.data.PublicationRequests.httpProtocol
import scenarios.data.PublicationScenarios
import utils.auth.OAuthAPI
import utils.auth.OAuthAPI.config.{onceUsers, rampUpUsers, rampUpUsersDuration}

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class CreatePublicationCivilSimulation extends Simulation {

  private val createPublicationCivilExec = scenario("CreatePublicationCivil")
    .exec(OAuthAPI.authData) // authenticate once per user
    .exec(PublicationScenarios.createPublicationCivil)


  setUp(
    createPublicationCivilExec.inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during (rampUpUsersDuration seconds)
    )
  ).protocols(httpProtocol)
    .assertions(
      details("Create Publication Civil request").responseTime.percentile(90).lt(1000)
    )
    .assertions(
      details("Create Publication Civil request").responseTime.percentile(95).lt(1500)
    )
    .assertions(
      details("Create Publication Civil request").responseTime.percentile(99).lt(2000)
    )
}


