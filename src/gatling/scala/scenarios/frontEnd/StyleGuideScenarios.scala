package scenarios.frontEnd

import io.gatling.core.Predef._
import requests.data.PublicationRequests
import scenarios.data.PublicationScenarios
import utils.ArtefactFileStore
import utils.auth.OAuthAPI

object StyleGuideScenarios {

  val createStyleGuideScenario = scenario("View Civil And Family Daily Cause List Style Guide")
    .exec(OAuthAPI.authData)
    .exec(PublicationScenarios.createPublicationCivilAndFamilyStyleGuide)
    .exec { session =>
      val artefactId = session("artefactId").as[String]
      ArtefactFileStore.write(artefactId)
      session
    }

  val viewStyleGuideScenario =
    scenario("View shared publication")
      .exec { session =>
        val artefactId = ArtefactFileStore.read()
        session.set("artefactId", artefactId)
      }
      .exec(PublicationRequests.viewCivilAndFamilyDailyCauseListStyleGuide)
}
