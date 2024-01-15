package scenarios.data

import io.gatling.core.Predef._
import requests.data.PublicationRequests
import requests.data.PublicationRequests.{courtListFeed, createDifferentSizePublicationFeed, createPublicationFeed}
import utils.auth.OAuthAPI

object PublicationScenarios {

  val CreatePublicationCivilScenario = scenario("Create Publication Civil scenario")
    .exec(OAuthAPI.authData)
    .feed(createPublicationFeed)
    .feed(courtListFeed)
    .exec(PublicationRequests.createPublicationCivilRequest)
    .exec(session => {
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber1").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber2").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber3").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber4").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber5").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber6").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber7").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber8").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber9").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber10").as[String] + "\n")
      session
    }
    )

  val CreatePublicationFamilyScenario = scenario("Create Publication Family scenario")
    .exec(OAuthAPI.authData)
    .feed(createPublicationFeed)
    .feed(courtListFeed)
    .exec(PublicationRequests.createPublicationFamilyRequest)
    .exec(session => {
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber1").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber2").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber3").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber4").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber5").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber6").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber7").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber8").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber9").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber10").as[String] + "\n")
      session
    }
    )

  val CreatePublicationCivilAndFamilyScenario = scenario("Create Publication Civil And Family scenario")
    .exec(OAuthAPI.authData)
    .feed(createPublicationFeed)
    .feed(courtListFeed)
    // Civil
    .exec(PublicationRequests.createPublicationCivilAndFamilyRequest)
    .exec(session => {
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber1").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber2").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber3").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber4").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber5").as[String] + "\n")
      session
    }
    )
    // Civil
    .exec(PublicationRequests.createPublicationCivilNextDayRequest)
    .exec(session => {
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber1").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber2").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber3").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber4").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber5").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber6").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber7").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber8").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber9").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber10").as[String] + "\n")
      session
    }
    )
    // Family
    .exec(PublicationRequests.createPublicationFamilyRequest)
    .exec(session => {
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber1").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber2").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber3").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber4").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber5").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber6").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber7").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber8").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber9").as[String] + "\n")
      scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv").appendAll(session("caseNumber10").as[String] + "\n")
      session
    }
    )

  val CreatePublicationDifferentFileSizeScenario = scenario("Create Publication Civil And Family Different File Sizes scenario")
    .exec(OAuthAPI.authData)
    .feed(createDifferentSizePublicationFeed)
    .feed(courtListFeed)
    .exec(PublicationRequests.createPublicationCivilAndFamilyTwoCasesRequest)
    .exec(PublicationRequests.createPublicationCivilAndFamilyFiftyCasesRequest)
    .exec(PublicationRequests.createPublicationCivilAndFamilyHundredCasesRequest)
    .exec(PublicationRequests.createPublicationCivilAndFamilyTwoHundredCasesRequest)
}
