package scenarios.channel

import io.gatling.core.Predef._
import requests.channel.PublicationRequests
import utils.auth.OAuthAPI

object PublicationScenarios {

  val generatePdfTwoCasesScenario = scenario("Generate PDF 2 cases")
    .exec(OAuthAPI.authChannel)
    .exec(PublicationRequests.postPublicationCivilAndFamilyTwoCasesRequest)

  val generatePdfFiftyCasesScenario = scenario("Generate PDF 50 cases")
    .exec(OAuthAPI.authChannel)
    .exec(PublicationRequests.postPublicationCivilAndFamilyFiftyCasesRequest)

  val generatePdfHundredCasesScenario = scenario("Generate PDF 100 cases")
    .exec(OAuthAPI.authChannel)
    .exec(PublicationRequests.postPublicationCivilAndFamilyHundredCasesRequest)

  val generatePdfTwoHundredCasesScenario = scenario("Generate PDF 200 cases")
    .exec(OAuthAPI.authChannel)
    .exec(PublicationRequests.postPublicationCivilAndFamilyTwoHundredCasesRequest)

  val generateArtefactSummaryTwoCasesScenario = scenario("Generate Artefact Summary 2 cases")
    .exec(OAuthAPI.authChannel)
    .exec(PublicationRequests.generateArtefactSummaryTwoCasesRequest)

  val generateArtefactSummaryFiftyCasesScenario = scenario("Generate Artefact Summary 50 cases")
    .exec(OAuthAPI.authChannel)
    .exec(PublicationRequests.generateArtefactSummaryFiftyCasesRequest)

  val generateArtefactSummaryHundredCasesScenario = scenario("Generate Artefact Summary 100 cases")
    .exec(OAuthAPI.authChannel)
    .exec(PublicationRequests.generateArtefactSummaryHundredCasesRequest)

  val generateArtefactSummaryTwoHundredCasesScenario = scenario("Generate Artefact Summary 200 cases")
    .exec(OAuthAPI.authChannel)
    .exec(PublicationRequests.generateArtefactSummaryTwoHundredCasesRequest)
}
