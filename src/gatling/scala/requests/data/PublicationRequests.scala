package requests.data

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder._
import utils.auth.OAuthAPI.config
import utils.headers.Headers

import java.time.LocalDateTime
import scala.util.Random

object PublicationRequests {

  // Set paths for endpoints
  private val PublicationsPath = "/publication"

  // Load court CSV file
  val courtListFeed = csv("courtLists/ReferenceData.csv").circular
  val httpProtocol = http.baseUrl(config.dataManagementApi.url)

  // Set feeders
  val createPublicationFeed =
    Iterator.continually(Map(
      "caseName" -> ("perfCaseName " + Random.nextInt(99999999)),
      "caseNumber1" -> ("perfCaseNumber " + Random.nextInt(99999999)),
      "caseNumber2" -> ("perfCaseNumber " + Random.nextInt(99999999)),
      "caseNumber3" -> ("perfCaseNumber " + Random.nextInt(99999999)),
      "caseNumber4" -> ("perfCaseNumber " + Random.nextInt(99999999)),
      "caseNumber5" -> ("perfCaseNumber " + Random.nextInt(99999999)),
      "caseNumber6" -> ("perfCaseNumber " + Random.nextInt(99999999)),
      "caseNumber7" -> ("perfCaseNumber " + Random.nextInt(99999999)),
      "caseNumber8" -> ("perfCaseNumber " + Random.nextInt(99999999)),
      "caseNumber9" -> ("perfCaseNumber " + Random.nextInt(99999999)),
      "caseNumber10" -> ("perfCaseNumber " + Random.nextInt(99999999)),
      "caseUrn" -> ("perfURN " + Random.nextInt(99999999)),
      "startDate" -> LocalDateTime.now(),
      "endDate" -> LocalDateTime.now().plusDays(1)
    ))

  val createDifferentSizePublicationFeed =
    Iterator.continually(Map(
      "startDate" -> LocalDateTime.now(),
      "endDate" -> LocalDateTime.now().plusDays(1)
    ))

  // Set requests
  val createPublicationCivilAndFamilyRequest: HttpRequestBuilder = http("Create Publication Civil And Family request")
    .post(PublicationsPath)
    .body(ElFileBody("data/daily-cause-list-civil-and-family/civilAndFamilyDailyCauseList.json"))
    .header("x-list-type", "CIVIL_AND_FAMILY_DAILY_CAUSE_LIST")
    .headers(Headers.headersAPI)
    .check(status is 201)

  // Set requests
  val createPublicationCivilAndFamilyTwoCasesRequest: HttpRequestBuilder = http("Create Publication Civil And Family request 2 cases")
    .post(PublicationsPath)
    .body(ElFileBody("data/daily-cause-list-civil-and-family/civilAndFamilyDailyCauseListTwoCases.json"))
    .header("x-list-type", "CIVIL_AND_FAMILY_DAILY_CAUSE_LIST")
    .headers(Headers.headersAPI)
    .check(status is 201)

  // Set requests
  val createPublicationCivilAndFamilyFiftyCasesRequest: HttpRequestBuilder = http("Create Publication Civil And Family request 50 cases")
    .post(PublicationsPath)
    .body(ElFileBody("data/daily-cause-list-civil-and-family/civilAndFamilyDailyCauseListFiftyCases.json"))
    .header("x-list-type", "CIVIL_AND_FAMILY_DAILY_CAUSE_LIST")
    .headers(Headers.headersAPI)
    .check(status is 201)

  // Set requests
  val createPublicationCivilAndFamilyHundredCasesRequest: HttpRequestBuilder = http("Create Publication Civil And Family request 100 cases")
    .post(PublicationsPath)
    .body(ElFileBody("data/daily-cause-list-civil-and-family/civilAndFamilyDailyCauseListHundredCases.json"))
    .header("x-list-type", "CIVIL_AND_FAMILY_DAILY_CAUSE_LIST")
    .headers(Headers.headersAPI)
    .check(status is 201)

  // Set requests
  val createPublicationCivilAndFamilyTwoHundredCasesRequest: HttpRequestBuilder = http("Create Publication Civil And Family request 200 cases")
    .post(PublicationsPath)
    .body(ElFileBody("data/daily-cause-list-civil-and-family/civilAndFamilyDailyCauseListTwoHundredCases.json"))
    .header("x-list-type", "CIVIL_AND_FAMILY_DAILY_CAUSE_LIST")
    .headers(Headers.headersAPI)
    .check(status is 201)

  // Set requests
  val createPublicationCivilRequest: HttpRequestBuilder = http("Create Publication Civil request")
    .post(PublicationsPath)
    .body(ElFileBody("data/daily-cause-list-civil/civilDailyCauseList.json"))
    .header("x-list-type", "CIVIL_DAILY_CAUSE_LIST")
    .headers(Headers.headersAPI)
    .check(status is 201)

  // Set requests
  val createPublicationCivilNextDayRequest: HttpRequestBuilder = http("Create Publication Civil Next Day request")
    .post(PublicationsPath)
    .body(ElFileBody("data/daily-cause-list-civil/civilDailyCauseList.json"))
    .header("x-list-type", "CIVIL_DAILY_CAUSE_LIST")
    .headers(Headers.headersAPI)
    .header("x-display-to", "${endDate}")
    .header("x-content-date", "${endDate}")
    .check(status is 201)

  // Set requests
  val createPublicationFamilyRequest: HttpRequestBuilder = http("Create Publication Family request")
    .post(PublicationsPath)
    .body(ElFileBody("data/daily-cause-list-family/familyDailyCauseList.json"))
    .header("x-list-type", "FAMILY_DAILY_CAUSE_LIST")
    .headers(Headers.headersAPI)
    .check(status is 201)
}
