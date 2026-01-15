package scenarios.data

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import requests.data.UploadHtmlFileRequest
import requests.data.PublicationRequests.{courtListFeed, createDifferentSizePublicationFeed}
import utils.auth.OAuthAPI.config

object UploadHtmlFileScenario {

  // Reusable chain to set requesterId
  val withRequesterId: ChainBuilder =
    exec(session => session.set("requesterId", config.testSystemAdminId))

  // Pure business flow
  val uploadHtmlPublicationFlow: ChainBuilder =
    exec(withRequesterId)                         // set requesterId
      .feed(createDifferentSizePublicationFeed)
      .feed(courtListFeed)
      .exec(UploadHtmlFileRequest.uploadHtmlPublicationRequest)
}
