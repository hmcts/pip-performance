package requests.data

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder._
import utils.auth.OAuthAPI.config
import utils.headers.Headers
import java.time.LocalDateTime

object UploadHtmlFileRequest {

  // Set paths for endpoints
  private val PublicationsPath = config.dataManagementApi.url + "/publication"

  // Load court CSV file
  val courtListFeed = csv("courtLists/ReferenceData.csv").circular
  val httpProtocol = http.baseUrl(config.dataManagementApi.url)

  // Set feeders
  val createDifferentSizePublicationFeed =
    Iterator.continually(Map(
      "startDate" -> LocalDateTime.now(),
      "endDate" -> LocalDateTime.now().plusDays(1)
    ))

  val filePath = getClass.getClassLoader.getResource("data/html-file/testFile.html").getPath

  val uploadHtmlPublicationRequest: HttpRequestBuilder = http("Create HTML Publication request")
    .post(PublicationsPath)
    .bodyPart(RawFileBodyPart("file", filePath).fileName("testFile.html").transferEncoding("binary"))
    .headers(Headers.headersUpladHtmlPublication)
    .check(status.is(201))
}