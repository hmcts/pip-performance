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

  val generateArtefactSummary = scenario("Generate Artefact Summary")
    .exec(OAuthAPI.authChannel)
    .exec(PublicationRequests.generateArtefactSummaryTwoCasesRequest)
    .exec(PublicationRequests.generateArtefactSummaryFiftyCasesRequest)
    .exec(PublicationRequests.generateArtefactSummaryHundredCasesRequest)
    .exec(PublicationRequests.generateArtefactSummaryTwoHundredCasesRequest)
}
