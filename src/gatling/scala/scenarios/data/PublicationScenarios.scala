package scenarios.data

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import requests.data.PublicationRequests
import requests.data.PublicationRequests._
import scenarios.combined.PublicationFlowScenarios.TestLocationid
import utils.auth.OAuthAPI
import utils.auth.OAuthAPI.config

import java.time.LocalDateTime
object PublicationScenarios {

  /* -----------------------------------
     Shared session initialisation
   ----------------------------------- */
  val withRequesterId: ChainBuilder =
    exec(session => session.set("requesterId", config.testSystemAdminId))

  /* -----------------------------------
     Case number persistence
   ----------------------------------- */
  private val caseFile = scala.reflect.io.File("src/gatling/resources/feederOutput/caseNumber.csv")
  private val TestLocationId = 3500

  def writeCaseNumbers(count: Int): ChainBuilder =
    exec { session =>
      (1 to count).foreach { i =>
        caseFile.appendAll(session(s"caseNumber$i").as[String] + "\n")
      }
      session
    }

  val write5  = writeCaseNumbers(5)
  val write10 = writeCaseNumbers(10)

  /* -----------------------------------
     Publication creation flows
   ----------------------------------- */
  val createPublicationCivil: ChainBuilder =
    exec(withRequesterId)
      .feed(createPublicationFeed)
      .feed(courtListFeed)
      .exec(PublicationRequests.createPublicationCivilRequest)
      .exec(write10)

  val createPublicationFamily: ChainBuilder =
    exec(withRequesterId)
      .feed(createPublicationFeed)
      .feed(courtListFeed)
      .exec(PublicationRequests.createPublicationFamilyRequest)
      .exec(write10)

  val createPublicationCivilAndFamily: ChainBuilder =
    exec(withRequesterId)
      .feed(createPublicationFeed)
      .feed(courtListFeed)
      .exec(PublicationRequests.createPublicationCivilAndFamilyRequest)
      .exec(write5)
      .exec(PublicationRequests.createPublicationCivilNextDayRequest)
      .exec(write10)
      .exec(PublicationRequests.createPublicationFamilyRequest)
      .exec(write10)

  val createPublicationCivilAndFamilyOnly: ChainBuilder =
    exec(withRequesterId)
      .feed(createPublicationFeed)
      .feed(courtListFeed)
      .exec(PublicationRequests.createPublicationCivilAndFamilyRequest)
      .exec(write5)

  val createPublicationCivilAndFamilyStyleGuide: ChainBuilder =
    exec(withRequesterId)
      .feed(createPublicationFeed)
      .feed(courtListFeed)
      .exec { session =>
        session
          .set("endDate", LocalDateTime.now().plusDays(1))
          .set("P&I ID", TestLocationId)
      }
      .exec(withRequesterId)
      .exec(PublicationRequests.createPublicationCivilAndFamilyRequestStyleGuide)
      .exec(write5)

  /* -----------------------------------
     Variable size payload flows
   ----------------------------------- */
  val createTwoCases: ChainBuilder =
    exec(withRequesterId)
      .feed(createDifferentSizePublicationFeed)
      .feed(courtListFeed)
      .exec(PublicationRequests.createPublicationCivilAndFamilyTwoCasesRequest)

  val createFiftyCases: ChainBuilder =
    exec(withRequesterId)
      .feed(createDifferentSizePublicationFeed)
      .feed(courtListFeed)
      .exec(PublicationRequests.createPublicationCivilAndFamilyFiftyCasesRequest)

  val createHundredCases: ChainBuilder =
    exec(withRequesterId)
      .feed(createDifferentSizePublicationFeed)
      .feed(courtListFeed)
      .exec(PublicationRequests.createPublicationCivilAndFamilyHundredCasesRequest)

  val createTwoHundredCases: ChainBuilder =
    exec(withRequesterId)
      .feed(createDifferentSizePublicationFeed)
      .feed(courtListFeed)
      .exec(PublicationRequests.createPublicationCivilAndFamilyTwoHundredCasesRequest)

  val create1MB: ChainBuilder =
    exec(withRequesterId)
      .feed(createDifferentSizePublicationFeed)
      .feed(courtListFeed)
      .exec(PublicationRequests.createPublicationCivilAndFamily1MBRequest)

  val create2MB: ChainBuilder =
    exec(withRequesterId)
      .feed(createDifferentSizePublicationFeed)
      .feed(courtListFeed)
      .exec(PublicationRequests.createPublicationCivilAndFamily2MBRequest)

  val create3MB: ChainBuilder =
    exec(withRequesterId)
      .feed(createDifferentSizePublicationFeed)
      .feed(courtListFeed)
      .exec(PublicationRequests.createPublicationCivilAndFamily3MBRequest)

  val create4MB: ChainBuilder =
    exec(withRequesterId)
      .feed(createDifferentSizePublicationFeed)
      .feed(courtListFeed)
      .exec(PublicationRequests.createPublicationCivilAndFamily4MBRequest)

  /* -----------------------------------
     CrownFirm PDDA flow
   ----------------------------------- */
  val createPublicationCrownFirmPdda: ChainBuilder =
    exec(withRequesterId)
      .feed(createDifferentSizePublicationFeed)
      .feed(courtListFeed)
      .exec(PublicationRequests.createPublicationCrownFirmPddaRequest)

  /* -----------------------------------
     PDF + Artefact flows
   ----------------------------------- */
  val generatePdfTwoCases: ChainBuilder =
    exec(PublicationRequests.getPublicationCivilAndFamilyTwoCasesRequest)

  val generatePdfFiftyCases: ChainBuilder =
    exec(PublicationRequests.getPublicationCivilAndFamilyFiftyCasesRequest)

  val generatePdfHundredCases: ChainBuilder =
    exec(PublicationRequests.getPublicationCivilAndFamilyHundredCasesRequest)

  val generateArtefactTwoCases: ChainBuilder =
    exec(PublicationRequests.generateArtefactSummaryTwoCasesRequest)

  val generateArtefactFiftyCases: ChainBuilder =
    exec(PublicationRequests.generateArtefactSummaryFiftyCasesRequest)

  val generateArtefactHundredCases: ChainBuilder =
    exec(PublicationRequests.generateArtefactSummaryHundredCasesRequest)

  val generateArtefactTwoHundredCases: ChainBuilder =
    exec(PublicationRequests.generateArtefactSummaryTwoHundredCasesRequest)

  val getPayloadHundredCases: ChainBuilder =
    exec(PublicationRequests.getPayloadCivilAndFamilyHundredCasesRequest)
}
