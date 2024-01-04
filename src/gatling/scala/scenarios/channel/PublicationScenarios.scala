package scenarios.channel

import io.gatling.core.Predef._
import requests.channel.PublicationRequests
import utils.auth.OAuthAPI

object PublicationScenarios {

  val generatePdfScenario = scenario("Generate PDF")
    .exec(OAuthAPI.authChannel)
    .exec(PublicationRequests.postPublicationCivilAndFamilyTwoCasesRequest)
    .exec(PublicationRequests.postPublicationCivilAndFamilyFiftyCasesRequest)
    .exec(PublicationRequests.postPublicationCivilAndFamilyHundredCasesRequest)
    .exec(PublicationRequests.postPublicationCivilAndFamilyTwoHundredCasesRequest)
}
