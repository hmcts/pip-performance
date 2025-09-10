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
  private val PublicationsPath = config.dataManagementApi.url + "/publication"

  private val artefactIdTwoCases = "23aa4c86-4ffe-477b-b5e5-c76b8f3a9a12"
  private val artefactIdFiftyCases = "f5aed02c-b6b7-440e-9f66-32029bd823cc"
  private val artefactIdHundredCases = "90889d83-088a-4ebf-bf68-781962cb5d3e"
  private val artefactIdTwoHundredCases = "33538ac4-7cba-475d-b091-d7c0003dd2a3"
  private val fileTypePath = "/PDF"

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

  val createPublicationCivilAndFamilyTwoCasesRequest: HttpRequestBuilder = http("Create Publication Civil And Family request 2 cases")
    .post(PublicationsPath)
    .body(ElFileBody("data/daily-cause-list-civil-and-family/civilAndFamilyDailyCauseListTwoCases.json"))
    .header("x-list-type", "CIVIL_AND_FAMILY_DAILY_CAUSE_LIST")
    .headers(Headers.headersAPI)
    .check(status is 201)

  val createPublicationCivilAndFamilyFiftyCasesRequest: HttpRequestBuilder = http("Create Publication Civil And Family request 50 cases")
    .post(PublicationsPath)
    .body(ElFileBody("data/daily-cause-list-civil-and-family/civilAndFamilyDailyCauseListFiftyCases.json"))
    .header("x-list-type", "CIVIL_AND_FAMILY_DAILY_CAUSE_LIST")
    .headers(Headers.headersAPI)
    .check(status is 201)

  val createPublicationCivilAndFamilyHundredCasesRequest: HttpRequestBuilder = http("Create Publication Civil And Family request 100 cases")
    .post(PublicationsPath)
    .body(ElFileBody("data/daily-cause-list-civil-and-family/civilAndFamilyDailyCauseListHundredCases.json"))
    .header("x-list-type", "CIVIL_AND_FAMILY_DAILY_CAUSE_LIST")
    .headers(Headers.headersAPI)
    .check(status is 201)

  val createPublicationCivilAndFamilyTwoHundredCasesRequest: HttpRequestBuilder = http("Create Publication Civil And Family request 200 cases")
    .post(PublicationsPath)
    .body(ElFileBody("data/daily-cause-list-civil-and-family/civilAndFamilyDailyCauseListTwoHundredCases.json"))
    .header("x-list-type", "CIVIL_AND_FAMILY_DAILY_CAUSE_LIST")
    .headers(Headers.headersAPI)
    .check(status is 201)

  val createPublicationCivilAndFamily1MBRequest: HttpRequestBuilder = http("Create Publication Civil And Family 1MB request")
    .post(PublicationsPath)
    .body(ElFileBody("data/daily-cause-list-civil-and-family/civilAndFamilyDailyCauseList1MB.json"))
    .header("x-list-type", "CIVIL_AND_FAMILY_DAILY_CAUSE_LIST")
    .headers(Headers.headersAPI)
    .check(status is 201)

  val createPublicationCivilAndFamily2MBRequest: HttpRequestBuilder = http("Create Publication Civil And Family 2MB request")
    .post(PublicationsPath)
    .body(ElFileBody("data/daily-cause-list-civil-and-family/civilAndFamilyDailyCauseList2MB.json"))
    .header("x-list-type", "CIVIL_AND_FAMILY_DAILY_CAUSE_LIST")
    .headers(Headers.headersAPI)
    .check(status is 201)

  val createPublicationCivilAndFamily3MBRequest: HttpRequestBuilder = http("Create Publication Civil And Family 3MB request")
    .post(PublicationsPath)
    .body(ElFileBody("data/daily-cause-list-civil-and-family/civilAndFamilyDailyCauseList3MB.json"))
    .header("x-list-type", "CIVIL_AND_FAMILY_DAILY_CAUSE_LIST")
    .headers(Headers.headersAPI)
    .check(status is 201)

  val createPublicationCivilAndFamily4MBRequest: HttpRequestBuilder = http("Create Publication Civil And Family 4MB request")
    .post(PublicationsPath)
    .body(ElFileBody("data/daily-cause-list-civil-and-family/civilAndFamilyDailyCauseList4MB.json"))
    .header("x-list-type", "CIVIL_AND_FAMILY_DAILY_CAUSE_LIST")
    .headers(Headers.headersAPI)
    .check(status is 201)

  val createPublicationCivilRequest: HttpRequestBuilder = http("Create Publication Civil request")
    .post(PublicationsPath)
    .body(ElFileBody("data/daily-cause-list-civil/civilDailyCauseList.json"))
    .header("x-list-type", "CIVIL_DAILY_CAUSE_LIST")
    .headers(Headers.headersAPI)
    .check(status is 201)

  val createPublicationCivilNextDayRequest: HttpRequestBuilder = http("Create Publication Civil Next Day request")
    .post(PublicationsPath)
    .body(ElFileBody("data/daily-cause-list-civil/civilDailyCauseList.json"))
    .header("x-list-type", "CIVIL_DAILY_CAUSE_LIST")
    .headers(Headers.headersAPI)
    .header("x-display-to", "${endDate}")
    .header("x-content-date", "${endDate}")
    .check(status is 201)

  val createPublicationFamilyRequest: HttpRequestBuilder = http("Create Publication Family request")
    .post(PublicationsPath)
    .body(ElFileBody("data/daily-cause-list-family/familyDailyCauseList.json"))
    .header("x-list-type", "FAMILY_DAILY_CAUSE_LIST")
    .headers(Headers.headersAPI)
    .check(status is 201)

  val getPublicationCivilAndFamilyTwoCasesRequest: HttpRequestBuilder = http("Generate PDF 2 Cases request")
    .get(PublicationsPath + "/" + artefactIdTwoCases + fileTypePath)
    .header("Authorization", "bearer ${bearerx}")
    .check(status is 200)

  val getPublicationCivilAndFamilyFiftyCasesRequest: HttpRequestBuilder = http("Generate PDF 50 Cases request")
    .get(PublicationsPath + "/" + artefactIdFiftyCases + fileTypePath)
    .header("Authorization", "bearer ${bearerx}")
    .check(status is 200)

  val getPublicationCivilAndFamilyHundredCasesRequest: HttpRequestBuilder = http("Generate PDF 100 Cases request")
    .get(PublicationsPath + "/" + artefactIdHundredCases + fileTypePath)
    .header("Authorization", "bearer ${bearerx}")
    .check(status is 200)

  val generateArtefactSummaryTwoCasesRequest: HttpRequestBuilder = http("Generate Artefact Summary 2 Cases request")
    .get(PublicationsPath + "/" + artefactIdTwoCases + "/summary")
    .header("Authorization", "bearer ${bearerx}")
    .check(status is 200)

  val generateArtefactSummaryFiftyCasesRequest: HttpRequestBuilder = http("Generate Artefact Summary 50 Cases request")
    .get(PublicationsPath + "/" + artefactIdFiftyCases + "/summary")
    .header("Authorization", "bearer ${bearerx}")
    .check(status is 200)

  val generateArtefactSummaryHundredCasesRequest: HttpRequestBuilder = http("Generate Artefact Summary 100 Cases request")
    .get(PublicationsPath + "/" + artefactIdHundredCases + "/summary")
    .header("Authorization", "bearer ${bearerx}")
    .check(status is 200)

  val generateArtefactSummaryTwoHundredCasesRequest: HttpRequestBuilder = http("Generate Artefact Summary 200 Cases request")
    .get(PublicationsPath + "/" + artefactIdTwoHundredCases + "/summary")
    .header("Authorization", "bearer ${bearerx}")
    .check(status is 200)

  val getPayloadCivilAndFamilyHundredCasesRequest: HttpRequestBuilder = http("Get payload 100 Cases request")
    .get(PublicationsPath + "/" + artefactIdHundredCases + "/payload")
    .header("Authorization", "bearer ${bearerx}")
    .check(status is 200)
}