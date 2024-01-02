package requests.channel

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder._
import utils.auth.OAuthAPI.config

object PublicationRequests {

  private val artefactIdTwoCases = "017e9e51-f970-475e-896a-164c70f4e60e"
  private val artefactIdFiftyCases = "f0f2446c-82e6-4154-bf78-b72f4278229d"
  private val artefactIdHundredCases = "e6f5b986-5659-40f0-af3a-6e947bf155c2"
  private val artefactIdTwoHundredCases = "88f38043-cd71-4843-a62a-6daf16f4c7f5"
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
