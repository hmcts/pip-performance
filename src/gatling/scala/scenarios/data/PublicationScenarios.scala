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

  val CreatePublicationCivilAndFamilyTwoCasesScenario = scenario("Create Publication Civil And Family 2 cases scenario")
    .exec(OAuthAPI.authData)
    .feed(createDifferentSizePublicationFeed)
    .feed(courtListFeed)
    .exec(PublicationRequests.createPublicationCivilAndFamilyTwoCasesRequest)

  val CreatePublicationCivilAndFamilyFiftyCasesScenario = scenario("Create Publication Civil And Family 50 cases scenario")
    .exec(OAuthAPI.authData)
    .feed(createDifferentSizePublicationFeed)
    .feed(courtListFeed)
    .exec(PublicationRequests.createPublicationCivilAndFamilyFiftyCasesRequest)

  val CreatePublicationCivilAndFamilyHundredCasesScenario = scenario("Create Publication Civil And Family 100 cases scenario")
    .exec(OAuthAPI.authData)
    .feed(createDifferentSizePublicationFeed)
    .feed(courtListFeed)
    .exec(PublicationRequests.createPublicationCivilAndFamilyHundredCasesRequest)

  val CreatePublicationCivilAndFamilyTwoHundredCasesScenario = scenario("Create Publication Civil And Family 200 cases scenario")
    .exec(OAuthAPI.authData)
    .feed(createDifferentSizePublicationFeed)
    .feed(courtListFeed)
    .exec(PublicationRequests.createPublicationCivilAndFamilyTwoHundredCasesRequest)

  val CreatePublicationCivilAndFamily1MBScenario = scenario("Create Publication Civil And Family 1MB scenario")
    .exec(OAuthAPI.authData)
    .feed(createDifferentSizePublicationFeed)
    .feed(courtListFeed)
    .exec(PublicationRequests.createPublicationCivilAndFamily1MBRequest)

  val CreatePublicationCivilAndFamily2MBScenario = scenario("Create Publication Civil And Family 2MB scenario")
    .exec(OAuthAPI.authData)
    .feed(createDifferentSizePublicationFeed)
    .feed(courtListFeed)
    .exec(PublicationRequests.createPublicationCivilAndFamily2MBRequest)

  val CreatePublicationCivilAndFamily3MBScenario = scenario("Create Publication Civil And Family 3MB scenario")
    .exec(OAuthAPI.authData)
    .feed(createDifferentSizePublicationFeed)
    .feed(courtListFeed)
    .exec(PublicationRequests.createPublicationCivilAndFamily3MBRequest)

  val CreatePublicationCivilAndFamily4MBScenario = scenario("Create Publication Civil And Family 4MB scenario")
    .exec(OAuthAPI.authData)
    .feed(createDifferentSizePublicationFeed)
    .feed(courtListFeed)
    .exec(PublicationRequests.createPublicationCivilAndFamily4MBRequest)
}
