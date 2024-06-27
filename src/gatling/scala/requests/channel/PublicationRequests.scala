package requests.channel

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder._
import utils.auth.OAuthAPI.config

object PublicationRequests {

  private val artefactIdTwoCases = "2fcbf066-4657-43c9-b9f1-8313f649820f"
  private val artefactIdFiftyCases = "74a21307-48f4-43e4-a2fc-25091d46613c"
  private val artefactIdHundredCases = "506c4973-0283-46a5-9c90-eaa76e029ea8"
  private val artefactIdTwoHundredCases = "0e74e020-a7b8-49a1-b141-59a944da7e8e"

  // Set paths for endpoints
  private val PublicationsPath = "/publication/"
  private val generateSummeryPublicationsPath = "/publication/summary/"

  val httpProtocol = http.baseUrl(config.channelManagementApi.url)

  // Set requests
  val postPublicationCivilAndFamilyTwoCasesRequest: HttpRequestBuilder = http("Generate PDF 2 Cases request")
    .post(PublicationsPath + artefactIdTwoCases)
    .header("Authorization", "bearer ${bearerx}")
    .check(status is 202)

  val postPublicationCivilAndFamilyFiftyCasesRequest: HttpRequestBuilder = http("Generate PDF 50 Cases request")
    .post(PublicationsPath + artefactIdFiftyCases)
    .header("Authorization", "bearer ${bearerx}")
    .check(status is 202)

  val postPublicationCivilAndFamilyHundredCasesRequest: HttpRequestBuilder = http("Generate PDF 100 Cases request")
    .post(PublicationsPath + artefactIdHundredCases)
    .header("Authorization", "bearer ${bearerx}")
    .check(status is 202)

  val postPublicationCivilAndFamilyTwoHundredCasesRequest: HttpRequestBuilder = http("Generate PDF 200 Cases request")
    .post(PublicationsPath + artefactIdTwoHundredCases)
    .header("Authorization", "bearer ${bearerx}")
    .check(status is 202)

  val generateArtefactSummaryTwoCasesRequest: HttpRequestBuilder = http("Generate Artefact Summary 2 Cases request")
    .get(generateSummeryPublicationsPath + artefactIdTwoCases)
    .header("Authorization", "bearer ${bearerx}")
    .check(status is 200)

  val generateArtefactSummaryFiftyCasesRequest: HttpRequestBuilder = http("Generate Artefact Summary 50 Cases request")
    .get(generateSummeryPublicationsPath + artefactIdFiftyCases)
    .header("Authorization", "bearer ${bearerx}")
    .check(status is 200)

  val generateArtefactSummaryHundredCasesRequest: HttpRequestBuilder = http("Generate Artefact Summary 100 Cases request")
    .get(generateSummeryPublicationsPath + artefactIdHundredCases)
    .header("Authorization", "bearer ${bearerx}")
    .check(status is 200)

  val generateArtefactSummaryTwoHundredCasesRequest: HttpRequestBuilder = http("Generate Artefact Summary 200 Cases request")
    .get(generateSummeryPublicationsPath + artefactIdTwoHundredCases)
    .header("Authorization", "bearer ${bearerx}")
    .check(status is 200)

}
