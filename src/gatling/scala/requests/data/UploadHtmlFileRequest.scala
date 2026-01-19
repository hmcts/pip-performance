package requests.data

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder._
import utils.auth.OAuthAPI.config
import utils.headers.Headers

import java.time.{LocalDate, LocalDateTime}
import scala.util.Random

object UploadHtmlFileRequest {

  // Set paths for endpoints
  private val PublicationsPath = config.dataManagementApi.url + "/publication"

  // Load court CSV file
  val courtListFeed = csv("courtLists/ReferenceData.csv").circular
  val httpProtocol = http.baseUrl(config.dataManagementApi.url)

  val numberOfDays = 5000
  val maxPastRangeDays = 20000 // must be >= numberOfDays

  require(
    maxPastRangeDays >= numberOfDays,
    "maxPastRangeDays must be >= numberOfDays to guarantee uniqueness"
  )

  val randomPastDays: Vector[LocalDate] =
    Random
      .shuffle((1 to maxPastRangeDays).toVector) // start at 1 => strictly past
      .take(numberOfDays)
      .map(offset => LocalDate.now().minusDays(offset))

  val createDifferentSizePublicationFeed =
    randomPastDays.iterator.map { day =>
      val start = day.atStartOfDay()
      Map(
        "startDate" -> start,
        "endDate"   -> LocalDateTime.now().plusDays(1)
      )
    }

  val filePath = getClass.getClassLoader.getResource("data/html-file/testFile.html").getPath

  val uploadHtmlPublicationRequest: HttpRequestBuilder = http("Create HTML Publication request")
    .post(PublicationsPath)
    .bodyPart(RawFileBodyPart("file", filePath).fileName("testFile.html").transferEncoding("binary"))
    .headers(Headers.headersUpladHtmlPublication)
    .check(status.is(201))
}