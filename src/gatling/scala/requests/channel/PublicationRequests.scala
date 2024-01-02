package requests.channel

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder._
import utils.auth.OAuthAPI.config

object PublicationRequests {

  private val artefactIdTwoCases = "d40fcd3b-b286-4ae7-b14d-fba309f737bf"
  private val artefactIdFiftyCases = "d95e82c6-58e0-4212-8881-aac049294d29"
  private val artefactIdHundredCases = "b5bf84aa-02d9-4efd-b108-166a0d6d3a34"
  private val artefactIdTwoHundredCases = "6f386993-0e51-4fef-a3b1-ebd661703a21"
  // Set paths for endpoints
  private val PublicationsPath = "/publication/"

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
}
